	@Test
	@TestForIssue( jiraKey = "")
	@RequiresDialect(H2Dialect.class)
	public void testBinaryRuntimeUsage() {
		StandardServiceRegistry ssr = buildStandardServiceRegistry( H2Dialect.class, true );
		try {
			final Metadata metadata = new MetadataSources( ssr )
					.addAnnotatedClass( UuidIdEntity.class )
					.buildMetadata();
			final SessionFactory sf = metadata.buildSessionFactory();
			try {
				Session s = sf.openSession();
				try {
					s.beginTransaction();
					s.byId( UuidIdEntity.class ).load( UUID.randomUUID() );
					s.getTransaction().commit();
				}
				finally {
					s.close();
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
