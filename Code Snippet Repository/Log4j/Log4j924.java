    public boolean add(final AppenderControl control) {
        boolean success;
        do {
            final AppenderControl[] original = appenderArray.get();
            for (final AppenderControl existing : original) {
                if (existing.equals(control)) {
                    return false; // the appender is already in the list
                }
            }
            final AppenderControl[] copy = Arrays.copyOf(original, original.length + 1);
            copy[copy.length - 1] = control;
            success = appenderArray.compareAndSet(original, copy);
        } while (!success); // could not swap: array was modified by another thread
        return true; // successfully added
    }
