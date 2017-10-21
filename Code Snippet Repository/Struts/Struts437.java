    public Map<URL, Set<String>> findPackagesMap(String uri) throws IOException {
        String basePath = path + uri;

        if (!basePath.endsWith("/")) {
            basePath += "/";
        }
        Enumeration<URL> urls = getResources(basePath);
        Map<URL, Set<String>> result = new HashMap<>();

        while (urls.hasMoreElements()) {
            URL location = urls.nextElement();

            try {
                if ("jar".equals(location.getProtocol())) {
                    Set<String> resources = new HashSet<>();
                    readJarDirectoryEntries(location, basePath, resources);
                    result.put(location, convertPathsToPackages(resources));
                } else if ("file".equals(location.getProtocol())) {
                    Set<String> resources = new HashSet<>();
                    readSubDirectories(new File(location.toURI()), uri, resources);
                    result.put(location, convertPathsToPackages(resources));
                }
            } catch (Exception e) {
                LOG.debug("Got exception finding subpackages for {}", uri, e);
            }
        }

        return result;
    }
