    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> endpointClasses)
    {
        Set<ServerEndpointConfig> configs = new HashSet<>();
        Class<?> endpointClass = SessionInfoSocket.class;
        configs.add(ServerEndpointConfig.Builder.create(endpointClass,"/info/{a}/").build());
        configs.add(ServerEndpointConfig.Builder.create(endpointClass,"/info/{a}/{b}/").build());
        configs.add(ServerEndpointConfig.Builder.create(endpointClass,"/info/{a}/{b}/{c}/").build());
        configs.add(ServerEndpointConfig.Builder.create(endpointClass,"/info/{a}/{b}/{c}/{d}/").build());
        endpointClass = SessionInfoEndpoint.class;
        configs.add(ServerEndpointConfig.Builder.create(endpointClass,"/einfo/").build());
        configs.add(ServerEndpointConfig.Builder.create(endpointClass,"/einfo/{a}/").build());
        configs.add(ServerEndpointConfig.Builder.create(endpointClass,"/einfo/{a}/{b}/").build());
        configs.add(ServerEndpointConfig.Builder.create(endpointClass,"/einfo/{a}/{b}/{c}/").build());
        configs.add(ServerEndpointConfig.Builder.create(endpointClass,"/einfo/{a}/{b}/{c}/{d}/").build());
        return configs;
    }
