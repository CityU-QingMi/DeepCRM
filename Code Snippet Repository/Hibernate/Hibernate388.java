    private EntityManagerFactory getEntityManagerFactory() {
        if ( emf == null ) {
            Bundle thisBundle = FrameworkUtil.getBundle(
                HibernateUtil.class
            );
            BundleContext context = thisBundle.getBundleContext();

            ServiceReference serviceReference = context.getServiceReference(
                PersistenceProvider.class.getName()
            );
            PersistenceProvider persistenceProvider = ( PersistenceProvider ) context
            .getService(
                serviceReference
            );

            emf = persistenceProvider.createEntityManagerFactory(
                "YourPersistenceUnitName",
                null
            );
        }
        return emf;
    }
