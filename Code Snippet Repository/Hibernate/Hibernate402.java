    @Override
    protected ProxyFactory buildProxyFactory(
            PersistentClass persistentClass,
            Getter idGetter,
            Setter idSetter) {
        return super.buildProxyFactory(
            persistentClass, idGetter,
            idSetter
        );
    }
