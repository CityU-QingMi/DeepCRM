    public Set<String> findPackages(String uri) throws IOException {
        String basePath = path + uri;

        Set<String> resources = new HashSet<>();
        if (!basePath.endsWith("/")) {
            basePath += "/";
        }
        Enumeration<URL> urls = getResources(basePath);

        while (urls.hasMoreElements()) {
            URL location = urls.nextElement();

            try {
                if ("jar".equals(location.getProtocol())) {
                    readJarDirectoryEntries(location, basePath, resources);
                } else if ("file".equals(location.getProtocol())) {
                    readSubDirectories(new File(location.toURI()), uri, resources);
                }
            } catch (Exception e) {
                LOG.debug("Got exception search for subpackages for {}", uri, e);
            }
        }

        return convertPathsToPackages(resources);
    }
