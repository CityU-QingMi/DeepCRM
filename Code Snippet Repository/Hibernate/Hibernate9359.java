	@Test
	public void testSqlAlterWithTableSchemaName() throws Exception {
		StandardServiceRegistry ssr = null;
		try {
			final Configuration cfg = buildConfiguration( SimpleNext.class );
			ssr = new StandardServiceRegistryBuilder(
					new BootstrapServiceRegistryBuilder().build(),
					cfg.getStandardServiceRegistryBuilder().getAggregatedCfgXml() )
					.applySettings( cfg.getProperties() )
					.build();
			SessionFactory sf = cfg.buildSessionFactory( ssr );
			try {
				Session session = sf.openSession();
				try {
					session.getTransaction().begin();
					session.createQuery( "FROM Simple", SimpleNext.class ).getResultList();
					session.getTransaction().commit();
				}
				catch ( Throwable t ) {
					if ( session.getTransaction().isActive() ) {
						session.getTransaction().rollback();
					}
					throw t;
				}
				finally {
					session.close();
				}
			}
			finally {
				sf.close();
			}
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
