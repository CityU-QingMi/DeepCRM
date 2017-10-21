    @Override
    protected List<ApplicationResource> getSources(ApplicationContext applicationContext) {
        Collection<ApplicationResource> resources = new ArrayList<>();

        Set<String> definitions = getTilesDefinitions(applicationContext.getInitParams());
        for (String definition : definitions) {
            resources.addAll(applicationContext.getResources(definition));
        }

        if (resources.contains(null)) {
            LOG.warn("Some resources were not found. Definitions: {}. Found resources: {}", definitions, resources);
        }

        List<ApplicationResource> filteredResources = new ArrayList<>();
        for (ApplicationResource resource : resources) {
            if (resource != null && Locale.ROOT.equals(resource.getLocale())) {
                filteredResources.add(resource);
            }
        }

        return filteredResources;
    }
