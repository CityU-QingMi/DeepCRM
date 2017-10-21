    @Inject
    public CompositeActionMapper(Container container,
                                 @Inject(value = StrutsConstants.STRUTS_MAPPER_COMPOSITE) String list) {
        String[] arr = StringUtils.split(StringUtils.trimToEmpty(list), ",");
        for (String name : arr) {
            Object obj = container.getInstance(ActionMapper.class, name);
            if (obj != null) {
                actionMappers.add((ActionMapper) obj);
            }
        }
    }
