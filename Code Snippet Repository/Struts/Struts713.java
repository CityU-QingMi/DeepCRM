    public String getUriFromActionMapping(ActionMapping mapping) {
        String namespace = mapping.getNamespace();
        for (int lastIndex = namespace.length(); lastIndex > (-1); lastIndex = namespace.lastIndexOf('/', lastIndex - 1)) {
            ActionMapper actionMapper = actionMappers.get(namespace.substring(0, lastIndex));
            if (actionMapper != null) {
                String uri = actionMapper.getUriFromActionMapping(mapping);
                LOG.debug("Using ActionMapper [{}]", actionMapper);
                if (uri != null) {
                    return uri;
                } else if (LOG.isDebugEnabled()) {
                    LOG.debug("ActionMapper [{}] failed to return an ActionMapping (null)", actionMapper);
                }
            }
        }
        LOG.debug("ActionMapper failed to return a uri");
        return null;
    }
