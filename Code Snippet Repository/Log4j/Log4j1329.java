    private static Object initializeJmDns() {
        try {
            jmDNSClass = LoaderUtil.loadClass("javax.jmdns.JmDNS");
            serviceInfoClass = LoaderUtil.loadClass("javax.jmdns.ServiceInfo");
            // if version 3 is available, use it to constuct a serviceInfo instance, otherwise support the version1 API
            boolean isVersion3 = false;
            try {
                // create method is in version 3, not version 1
                jmDNSClass.getMethod("create");
                isVersion3 = true;
            } catch (final NoSuchMethodException e) {
                // no-op
            }

            if (isVersion3) {
                return createJmDnsVersion3();
            }
            return createJmDnsVersion1();
        } catch (final ClassNotFoundException | ExceptionInInitializerError e) {
            LOGGER.warn("JmDNS or serviceInfo class not found", e);
        }
        return null;
    }
