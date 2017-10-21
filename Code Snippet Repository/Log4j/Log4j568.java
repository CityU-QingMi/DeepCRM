        public static LoggerContext getContext() {
            ClassLoader cl = TomcatLogger.class.getClassLoader();
            URI uri = null;
            for (String fileName : FILE_NAMES) {
                try {
                    URL url = cl.getResource(fileName);
                    if (url != null) {
                        uri = url.toURI();
                        break;
                    }
                } catch (URISyntaxException ex) {
                    // Ignore the exception.
                }
            }
            if (uri == null) {
                return getContext(FQCN, cl, false);
            } else {
                return getContext(FQCN, cl, false, uri, "Tomcat");
            }
        }
