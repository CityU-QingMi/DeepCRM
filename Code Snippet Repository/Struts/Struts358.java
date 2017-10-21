    private void clearTomcatCache() {
        ClassLoader loader = getCurrentThreadContextClassLoader();
        // no need for compilation here.
        Class cl = loader.getClass();

        try {
            if ("org.apache.catalina.loader.WebappClassLoader".equals(cl.getName())) {
                clearMap(cl, loader, TOMCAT_RESOURCE_ENTRIES_FIELD);
            } else {
                LOG.debug("Class loader {} is not tomcat loader.", cl.getName());
            }
        } catch (NoSuchFieldException nsfe) {
            if ("org.apache.catalina.loader.WebappClassLoaderBase".equals(cl.getSuperclass().getName())) {
                LOG.debug("Base class {} doesn't contain '{}' field, trying with parent!", cl.getName(), TOMCAT_RESOURCE_ENTRIES_FIELD, nsfe);
                try {
                    clearMap(cl.getSuperclass(), loader, TOMCAT_RESOURCE_ENTRIES_FIELD);
                } catch (Exception e) {
                    LOG.warn("Couldn't clear tomcat cache using {}", cl.getSuperclass().getName(), e);
                }
            }
        } catch (Exception e) {
            LOG.warn("Couldn't clear tomcat cache", cl.getName(), e);
        }
    }
