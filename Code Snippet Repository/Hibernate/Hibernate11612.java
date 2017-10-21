    protected boolean isExcluded( String className ) {

        if (excludedClasses != null) {
            for (int i = 0; i < excludedClasses.length; i++) {
                if (className.startsWith(excludedClasses[i])) {
                    return true;
                }
            }
        }

        return false;
    }
