	public void testAuditedDynamicComponentFailure() throws URISyntaxException {
		final Configuration config = new Configuration();
		final URL hbm = Thread.currentThread().getContextClassLoader().getResource(
				"mappings/dynamicComponents/mapAudited.hbm.xml"
		);
		config.addFile( new File( hbm.toURI() ) );

		final String auditStrategy = getAuditStrategy();
		if ( !StringTools.isEmpty( auditStrategy ) ) {
			config.setProperty( EnversSettings.AUDIT_STRATEGY, auditStrategy );
		}

		final ServiceRegistry serviceRegistry = ServiceRegistryBuilder.buildServiceRegistry( config.getProperties() );
		try {
			config.buildSessionFactory( serviceRegistry );
			Assert.fail( "MappingException expected" );
		}
		catch ( MappingException e ) {
			Assert.assertEquals(
					"Audited dynamic-component properties are not supported. Consider applying @NotAudited annotation to "
							+ AuditedDynamicComponentEntity.class.getName() + "#customFields.",
					e.getMessage()
			);
		}
		finally {
			ServiceRegistryBuilder.destroy( serviceRegistry );
		}
	}
