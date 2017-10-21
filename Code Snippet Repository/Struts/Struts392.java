    public void findInPackage(Test test, String packageName) {
        packageName = packageName.replace('.', '/');
        ClassLoader loader = getClassLoader();
        Enumeration<URL> urls;

        try {
            urls = loader.getResources(packageName);
        }
        catch (IOException ioe) {
            if (LOG.isWarnEnabled()) {
        	LOG.warn("Could not read package: " + packageName, ioe);
            }
            return;
        }

        while (urls.hasMoreElements()) {
            try {
                String urlPath = urls.nextElement().getFile();
                urlPath = URLDecoder.decode(urlPath, "UTF-8");

                // If it's a file in a directory, trim the stupid file: spec
                if ( urlPath.startsWith("file:") ) {
                    urlPath = urlPath.substring(5);
                }

                // Else it's in a JAR, grab the path to the jar
                if (urlPath.indexOf('!') > 0) {
                    urlPath = urlPath.substring(0, urlPath.indexOf('!'));
                }

                if (LOG.isInfoEnabled()) {
                    LOG.info("Scanning for classes in [" + urlPath + "] matching criteria: " + test);
                }
                File file = new File(urlPath);
                if ( file.isDirectory() ) {
                    loadImplementationsInDirectory(test, packageName, file);
                }
                else {
                    loadImplementationsInJar(test, packageName, file);
                }
            }
            catch (IOException ioe) {
                if (LOG.isWarnEnabled()) {
                    LOG.warn("could not read entries", ioe);
                }
            }
        }
    }
