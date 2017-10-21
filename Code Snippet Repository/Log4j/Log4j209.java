    static Collection<UrlResource> findUrlResources(final String resource) {
        final ClassLoader[] candidates = {getThreadContextClassLoader(), LoaderUtil.class.getClassLoader(),
                GET_CLASS_LOADER_DISABLED ? null : ClassLoader.getSystemClassLoader()};
        final Collection<UrlResource> resources = new LinkedHashSet<>();
        for (final ClassLoader cl : candidates) {
            if (cl != null) {
                try {
                    final Enumeration<URL> resourceEnum = cl.getResources(resource);
                    while (resourceEnum.hasMoreElements()) {
                        resources.add(new UrlResource(cl, resourceEnum.nextElement()));
                    }
                } catch (final IOException e) {
                    LowLevelLogUtil.logException(e);
                }
            }
        }
        return resources;
    }
