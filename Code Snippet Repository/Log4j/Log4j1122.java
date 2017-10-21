    private static Class<? extends StringMap> createCachedClass(final String className) {
        if (className == null) {
            return null;
        }
        try {
            return LoaderUtil.loadClass(className).asSubclass(StringMap.class);
        } catch (final Exception any) {
            return null;
        }
    }
