    public String getUriFromActionMapping(ActionMapping mapping) {

        for (ActionMapper actionMapper : actionMappers) {
            String uri = actionMapper.getUriFromActionMapping(mapping);
            LOG.debug("Using ActionMapper: {}", actionMapper);
            if (uri == null) {
                LOG.debug("ActionMapper {} failed to return an ActionMapping (null)", actionMapper);
            }
            else {
                return uri;
            }
        }
        LOG.debug("exhausted from ActionMapper that could return an ActionMapping");
        return null;
    }
