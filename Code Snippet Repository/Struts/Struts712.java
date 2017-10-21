    @SuppressWarnings("")
    public ActionMapping getMapping(HttpServletRequest request, ConfigurationManager configManager) {
        String uri = RequestUtils.getUri(request);
        for (int lastIndex = uri.lastIndexOf('/'); lastIndex > (-1); lastIndex = uri.lastIndexOf('/', lastIndex - 1)) {
            ActionMapper actionMapper = actionMappers.get(uri.substring(0, lastIndex));
            if (actionMapper != null) {
                ActionMapping actionMapping = actionMapper.getMapping(request, configManager);
                LOG.debug("Using ActionMapper [{}]", actionMapper);
                if (actionMapping != null) {
                    if (LOG.isDebugEnabled()) {
                        if (actionMapping.getParams() != null) {
                            LOG.debug("ActionMapper found mapping. Parameters: [{}]", actionMapping.getParams().toString());
                            for (Map.Entry<String, Object> mappingParameterEntry : actionMapping.getParams().entrySet()) {
                                Object paramValue = mappingParameterEntry.getValue();
                                if (paramValue == null) {
                                    LOG.debug("[{}] : null!", mappingParameterEntry.getKey());
                                } else if (paramValue instanceof String[]) {
                                    LOG.debug("[{}] : (String[]) {}", mappingParameterEntry.getKey(), Arrays.toString((String[]) paramValue));
                                } else if (paramValue instanceof String) {
                                    LOG.debug("[{}] : (String) [{}]", mappingParameterEntry.getKey(), paramValue.toString());
                                } else {
                                    LOG.debug("[{}] : (Object) [{}]", mappingParameterEntry.getKey(), paramValue.toString());
                                }
                            }
                        }
                    }
                    return actionMapping;
                } else {
                    LOG.debug("ActionMapper [{}] failed to return an ActionMapping", actionMapper);
                }
            }
        }
        LOG.debug("No ActionMapper found");
        return null;
    }
