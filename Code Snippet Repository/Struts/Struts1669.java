    public void register(ContainerBuilder builder, LocatableProperties props) throws ConfigurationException {
        if (!builder.contains(ObjectFactory.class)) {
            builder.factory(ObjectFactory.class);
        }
        if (!builder.contains(ActionProxyFactory.class)) {
            builder.factory(ActionProxyFactory.class, DefaultActionProxyFactory.class);
        }
        if (!builder.contains(ExcludedPatternsChecker.class)) {
            builder.factory(ExcludedPatternsChecker.class, DefaultExcludedPatternsChecker.class);
        }
    }
