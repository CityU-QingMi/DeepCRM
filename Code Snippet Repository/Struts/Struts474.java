    public static void addFinder(LocationFinder finder) {
        if (finder == null) {
            return;
        }

        synchronized(LocationFinder.class) {
            // Update a clone of the current finder list to avoid breaking
            // any iteration occuring in another thread.
            List<WeakReference<LocationFinder>> newFinders = new ArrayList<>(finders);
            newFinders.add(new WeakReference<LocationFinder>(finder));
            finders = newFinders;
        }
    }
