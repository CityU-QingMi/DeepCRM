    public Map<String, URL> getResourcesMap(String uri) throws IOException {
        String basePath = path + uri;

        Map<String, URL> resources = new HashMap<>();
        if (!basePath.endsWith("/")) {
            basePath += "/";
        }
        Enumeration<URL> urls = getResources(basePath);

        while (urls.hasMoreElements()) {
            URL location = urls.nextElement();

            try {
                if ("jar".equals(location.getProtocol())) {
                    readJarEntries(location, basePath, resources);
                } else if ("file".equals(location.getProtocol())) {
                    readDirectoryEntries(location, resources);
                }
            } catch (Exception e) {
                LOG.debug("Got exception loading resources for {}", uri, e);
            }
        }

        return resources;
    }
