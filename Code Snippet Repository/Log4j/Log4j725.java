    public static JpaDatabaseManager getJPADatabaseManager(final String name, final int bufferSize,
                                                           final Class<? extends AbstractLogEventWrapperEntity>
                                                                   entityClass,
                                                           final Constructor<? extends AbstractLogEventWrapperEntity>
                                                                   entityConstructor,
                                                           final String persistenceUnitName) {

        return AbstractDatabaseManager.getManager(
                name, new FactoryData(bufferSize, entityClass, entityConstructor, persistenceUnitName), FACTORY
        );
    }
