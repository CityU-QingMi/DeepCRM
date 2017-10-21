    public AppenderControl remove(final String name) {
        boolean success;
        do {
            success = true;
            final AppenderControl[] original = appenderArray.get();
            for (int i = 0; i < original.length; i++) {
                final AppenderControl appenderControl = original[i];
                if (Objects.equals(name, appenderControl.getAppenderName())) {
                    final AppenderControl[] copy = removeElementAt(i, original);
                    if (appenderArray.compareAndSet(original, copy)) {
                        return appenderControl; // successfully removed
                    }
                    success = false; // could not swap: array was modified by another thread
                    break;
                }
            }
        } while (!success);
        return null; // not found
    }
