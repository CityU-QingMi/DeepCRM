    private static List<URL> getURLs(ClassLoaderInterface classLoader, String[] dirNames) {
        List<URL> urls = new ArrayList<>();
        for (String dirName : dirNames) {
            try {
                Enumeration<URL> classLoaderURLs = classLoader.getResources(dirName);
                while (classLoaderURLs.hasMoreElements()) {
                    URL url = classLoaderURLs.nextElement();
                    urls.add(url);
                }
            } catch (IOException ioe) {
                LOG.error("Could not read directory [{}]", dirName, ioe);
            }
        }

        return urls;
    }
