	@After
	public void cleanup() {
		// Drops the table after the sql alter test.
		StandardServiceRegistry ssr = null;
		try {
			// build simple configuration
			final Configuration cfg = buildConfiguration( SimpleFirst.class );

			// Build Standard Service Registry
			ssr = new StandardServiceRegistryBuilder(
					new BootstrapServiceRegistryBuilder().build(),
					cfg.getStandardServiceRegistryBuilder().getAggregatedCfgXml()
			)
					.applySettings( cfg.getProperties() )
					.build();

			SessionFactory sf = cfg.buildSessionFactory( ssr );
			try {
				Session session = sf.openSession();
				try {
					session.getTransaction().begin();
					session.createNativeQuery( "DROP TABLE Simple" ).executeUpdate();
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
