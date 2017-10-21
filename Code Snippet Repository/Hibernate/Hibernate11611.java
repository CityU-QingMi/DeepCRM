    protected boolean isIncluded( String className ) {

        if (includedClasses != null) {
            for (int i = 0; i < includedClasses.length; i++) {
                if (className.startsWith(includedClasses[i])) {
                    return true;
                }
            }
        }

        return false;
    }
