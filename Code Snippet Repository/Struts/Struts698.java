    public ActionMapping getMappingFromActionName(String actionName) {

        for (ActionMapper actionMapper : actionMappers) {
            ActionMapping actionMapping = actionMapper.getMappingFromActionName(actionName);
            LOG.debug("Using ActionMapper: {}", actionMapper);
            if (actionMapping == null) {
                LOG.debug("ActionMapper {} failed to return an ActionMapping (null)", actionMapper);
            }
            else {
                return actionMapping;
            }
        }
        LOG.debug("exhausted from ActionMapper that could return an ActionMapping");
        return null;
    }
