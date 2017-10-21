    protected Set<ApplicationResource> findResources(String path) throws IOException {
        Set<ApplicationResource> resources = new HashSet<>();

        LOG.trace("Using ResourceFinder to find matches for {}", path);

        Pattern pattern = WildcardUtil.compileWildcardPattern(path);
        Map<String, URL> matches = finder.getResourcesMap("");

        for (String resource : matches.keySet()) {
            if (pattern.matcher(resource).matches()) {
                URL url = matches.get(resource);
                resources.add(new StrutsApplicationResource(url));
            }
        }

        LOG.trace("Found resources {} for path {}", resources, path);
        return resources;
    }
