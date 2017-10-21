    public Collection<ApplicationResource> getResources(String path) {
        Set<ApplicationResource> resources = new HashSet<>();

        if (path.startsWith("/")) {
            LOG.trace("Using ServletContext to load resource {}", path);
            ApplicationResource resource = getResource(path);
            if (resource != null) {
                resources.add(resource);
            }
        }

        try {
            resources.addAll(findResources(path));
        } catch (IOException e) {
            LOG.error("Cannot find resources for [{}]", path, e);
        }

        return resources;
    }
