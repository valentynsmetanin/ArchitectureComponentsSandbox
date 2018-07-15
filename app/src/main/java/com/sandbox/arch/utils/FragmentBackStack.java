package com.sandbox.arch.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FragmentBackStack {
    private FragmentManager fragmentManager;
    private int fragmentContainerID;

    private List<Fragment> mFragments;

    public FragmentBackStack(FragmentManager fragmentManager, int fragmentContainerID) {
        this.fragmentManager = fragmentManager;
        this.fragmentContainerID = fragmentContainerID;
        this.mFragments = new LinkedList<>();
    }

    /**
     * Set new fragment
     *
     * @param fragment       for <b>replace</b>, or <b>push</b> to stack
     * @param addToBackStack set <code>true</code> for push in stack
     */
    private synchronized void set(Fragment fragment, boolean addToBackStack) {
        if (!addToBackStack) {

            fragmentManager.beginTransaction()
                    .replace(fragmentContainerID, fragment, fragment.getClass().getSimpleName())
                    .setTransition(FragmentTransaction.TRANSIT_NONE).commit();

            if(mFragments.size() > 0) {
                mFragments.remove(mFragments.size() - 1);
            }
            mFragments.add(fragment);
        } else {
            fragmentManager.beginTransaction().replace(fragmentContainerID, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_NONE)
                    .addToBackStack(fragment.getClass().getSimpleName()).commit();

            mFragments.add(fragment);
        }
    }

    /**
     * Add new fragment
     *
     * @param fragment for <b>add</b> to stack
     */
    public void push(Fragment fragment) {
        set(fragment, true);
    }

    /**
     * Set new fragment
     *
     * @param fragment for <b>replace</b> and CLEAR stack
     */
    public void replace(Fragment fragment) {
        set(fragment, false);
    }

    /**
     * Return to root and set new fragment
     *
     * @param fragment for <b>replace</b> and CLEAR to root fragment
     */
    public synchronized void replaceWithReturnToRoot(Fragment fragment) {
        List<Fragment> removeList = new ArrayList<>();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        for (int i = mFragments.size() - 1; i >= 0; i--) {
            Fragment f = mFragments.get(i);
            transaction.remove(f);
            popBackStack(true);

            removeList.add(f);
        }

        transaction.replace(fragmentContainerID, fragment, fragment.getClass().getSimpleName())
                .setTransition(FragmentTransaction.TRANSIT_NONE).commit();

        mFragments.removeAll(removeList);
        mFragments.add(fragment);
    }


    /**
     * Get fragment name
     *
     * @return fragment tag
     */
    public String peek() {
        if (getStackEntryCount() > 0) {
            return fragmentManager.getBackStackEntryAt(getStackEntryCount() - 1).getName();
        } else {
            Fragment fragment = fragmentManager.findFragmentById(fragmentContainerID);

            if (fragment != null) {
                return fragment.getTag();
            } else {
                return "";
            }
        }
    }

    public synchronized Fragment getTopFragment() {
        return fragmentManager.findFragmentById(fragmentContainerID);
    }

    public synchronized void pop() {
        popBackStack(false);
    }

    private synchronized void popBackStack(boolean popImmediate) {
        if (popImmediate) {
            fragmentManager.popBackStackImmediate();
        } else {
            fragmentManager.popBackStack();
        }

        if(mFragments.size() > 0) {
            mFragments.remove(mFragments.size() - 1);
        }
    }

    /**
     * Remove [Main] fragment <b>(only one and from his container)</b>
     *
     * @param fragment root fragment
     */
    public synchronized void removeRoot(Fragment fragment) {
        fragmentManager.beginTransaction().remove(fragment).commit();
        mFragments.remove(fragment);
    }

    /**
     * Clear all fragments except [Main] fragment
     */
    public synchronized boolean clear() {
        boolean result;
        try {
            result = fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            mFragments.clear();
        } catch (IllegalStateException ignored) {
            result = false;
        }
        return result;
    }

    /**
     * Remove all fragments above (or with) specified fragment
     *
     * @param inclusive true if specified fragment should be popped
     */
    public synchronized boolean raise(String fragmentTag, boolean inclusive) {
        boolean result;
        try {
            result = fragmentTag != null &&
                    fragmentManager.popBackStackImmediate(fragmentTag, inclusive ? FragmentManager.POP_BACK_STACK_INCLUSIVE : 0);

            mFragments.clear();
        } catch (IllegalStateException ignored) {
            result = false;
        }
        return result;

    }

    /**
     * Pop fragments to target
     *
     * @param clazz class of target fragment
     */
    public synchronized void returnTo(Class<?> clazz) {
        String fragmentTag = clazz.getSimpleName();
        if (containsInContainer(fragmentTag)) {
            while (!peek().equals(fragmentTag) &&
                    (!fragmentManager.getFragments().isEmpty()
                            || fragmentManager.getBackStackEntryCount() != 0)) {
                fragmentManager.popBackStackImmediate();
                if(mFragments.size() > 0) {
                    mFragments.remove(mFragments.size() - 1);
                }
            }
        }
    }

    /**
     * Check fragment contains in back stack
     *
     * @param fragmentTag tag of target fragment
     * @return contain fragment in back stack
     */
    public boolean contains(String fragmentTag) {
        if (fragmentTag != null) {
            int count = getStackEntryCount();

            for (int entry = 0; entry < count; entry++) {
                if (fragmentTag.equals(fragmentManager.getBackStackEntryAt(entry).getName())) {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * Check fragment contains in container
     *
     * @param fragmentTag tag of target fragment
     * @return contain fragment in container (including back stack)
     */
    private boolean containsInContainer(String fragmentTag) {
        if (fragmentTag == null || fragmentTag.isEmpty()) return false;

        Fragment fragmentByTag = fragmentManager.findFragmentByTag(fragmentTag);
        return fragmentByTag != null;
    }

    /**
     * @return count of fragments in back stack
     */
    public int getStackEntryCount() {
        return fragmentManager.getBackStackEntryCount();
    }

    public static class Retainer extends Fragment {
        // data object we want to retain
        private FragmentBackStack backStack;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        /**
         * Set back stack
         *
         * @param backStack **set** for retain. As rule called in Activity::onDestroy()
         */
        public void retain(FragmentBackStack backStack) {
            this.backStack = backStack;
        }

        /**
         * Get retained back stack
         *
         * @param fragmentManager **set** fragment manager from current activity
         * @return retained stack
         */
        public FragmentBackStack restore(FragmentManager fragmentManager) {
            if(backStack != null) {
                backStack.fragmentManager = fragmentManager;
            }
            return backStack;
        }

        public static Retainer newInstance() {
            return new Retainer();
        }
    }

}