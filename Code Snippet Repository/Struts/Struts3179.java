    public static GxpInstance getInstance(String gxpPath) {
        try {
            return pathToGxpInstance.get(gxpPath);
        } catch (RuntimeException e) {
            if (e.getCause() instanceof ClassNotFoundException) {
                // Couldn't find or load the GXP class.  Return null.
                // It would be simpler if classToGxp.create() could return null,
                // but the contract of ReferenceCache doesn't allow it to.
                return null;
            }
            throw e;
        }
    }
