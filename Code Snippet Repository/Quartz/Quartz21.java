    public QuartzObjectMapperProvider() {
        ObjectMapper objectMapper = new ObjectMapper();
        SerializationConfig dflt = objectMapper.getSerializationConfig();
        objectMapper.setSerializationConfig(dflt.withPropertyNamingStrategy(new PropertyNamingStrategy() {

/**/
/**/
/**/
/**/
/**/
            @Override
            public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                if (ROOT_METHOD_NAME.equals(method.getName()))
                    return QUARTZ_ROOT_NAME;
                else
                    return super.nameForGetterMethod(config, method, defaultName);
            }
        }));

        this.om = objectMapper;
    }
