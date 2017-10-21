    protected boolean isNotFound( String className ) {

        if (notFoundClasses != null) {
            for (int i = 0; i < notFoundClasses.length; i++) {
                if (className.startsWith(notFoundClasses[i])) {
                    return true;
                }
            }
        }

        return false;
    }
