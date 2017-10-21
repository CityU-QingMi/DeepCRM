    private void initClassPath() {

        URL[] urls = parentClassLoader.getURLs();
        StringBuffer cpath = new StringBuffer();
        String sep = System.getProperty("path.separator");

        for (int i = 0; i < urls.length; i++) {
            // Tomcat 4 can use URL's other than file URL's,
            // a protocol other than file: will generate a
            // bad file system path, so only add file:
            // protocol URL's to the classpath.

            if (urls[i].getProtocol().equals("file")) {
                cpath.append((String) urls[i].getFile() + sep);
            }
        }

        cpath.append(options.getScratchDir() + sep);

        String cp = (String) context.getAttribute(Constants.SERVLET_CLASSPATH);
        if (cp == null || cp.equals("")) {
            cp = options.getClassPath();
        }

        classpath = cpath.toString() + cp;

        if (log.isDebugEnabled()) {
            log.debug("Compilation classpath initialized: " + getClassPath());
        }
    }
