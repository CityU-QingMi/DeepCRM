		@Override
      protected org.infinispan.transaction.lookup.TransactionManagerLookup createTransactionManagerLookup(SessionFactoryOptions settings, Properties properties) {
         return new HibernateTransactionManagerLookup(null, null) {
            @Override
            public TransactionManager getTransactionManager() throws Exception {
               AbstractJtaPlatform jta = new JBossStandAloneJtaPlatform();
               jta.injectServices(ServiceRegistryBuilder.buildServiceRegistry());
               return jta.getTransactionManager();
            }
         };
      }
