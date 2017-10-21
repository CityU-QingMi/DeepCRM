    @Inject(StrutsConstants.PREFIX_BASED_MAPPER_CONFIGURATION)
    public void setPrefixBasedActionMappers(String list) {
        String[] mappers = StringUtils.split(StringUtils.trimToEmpty(list), ",");
        for (String mapper : mappers) {
            String[] thisMapper = mapper.split(":");
            if (thisMapper.length == 2) {
                String mapperPrefix = thisMapper[0].trim();
                String mapperName = thisMapper[1].trim();
                Object obj = container.getInstance(ActionMapper.class, mapperName);
                if (obj != null) {
                    actionMappers.put(mapperPrefix, (ActionMapper) obj);
                } else {
                    LOG.debug("invalid PrefixBasedActionMapper config entry: [{}]", mapper);
                }
            }
        }
    }
