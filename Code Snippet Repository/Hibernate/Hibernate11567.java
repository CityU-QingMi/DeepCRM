	 @Override
	 protected void configureStandardServiceRegistryBuilder(StandardServiceRegistryBuilder ssrb) {
		  super.configureStandardServiceRegistryBuilder(ssrb);
		  ssrb.addService(MultiTenantConnectionProvider.class, new AbstractMultiTenantConnectionProvider() {

				@Override
				protected ConnectionProvider getAnyConnectionProvider() {
					 return db1;
				}

				@Override
				protected ConnectionProvider selectConnectionProvider(String tenantIdentifier) {
					 if (DB1.equals(tenantIdentifier)) return db1;
					 if (DB2.equals(tenantIdentifier)) return db2;
					 throw new IllegalArgumentException();
				}
		  });
	 }
