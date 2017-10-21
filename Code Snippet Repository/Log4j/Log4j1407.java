    private int loadNoLookups(final String[] options) {
        if (options != null) {
            for (int i = 0; i < options.length; i++) {
                final String option = options[i];
                if (NOLOOKUPS.equalsIgnoreCase(option)) {
                    return i;
                }
            }
        }
        return -1;
    }
