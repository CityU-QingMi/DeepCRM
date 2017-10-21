    @Inject(StrutsConstants.PREFIX_BASED_MAPPER_CONFIGURATION)
    public void setPrefixBasedActionProxyFactories(String list) {
        if (list != null) {
            String[] factories = list.split(",");
            for (String factory : factories) {
                String[] thisFactory = factory.split(":");
                if (thisFactory.length == 2) {
                    String factoryPrefix = thisFactory[0].trim();
                    String factoryName = thisFactory[1].trim();
                    ActionProxyFactory obj = container.getInstance(ActionProxyFactory.class, factoryName);
                    if (obj != null) {
                        actionProxyFactories.put(factoryPrefix, obj);
                    } else {
                        LOG.warn("Invalid PrefixBasedActionProxyFactory config entry: [{}]", factory);
                    }
                }
            }
        }
    }
