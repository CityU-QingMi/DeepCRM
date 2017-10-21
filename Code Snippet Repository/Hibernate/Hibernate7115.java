	@Test
	public void testCfgXmlWithSchemaLocation() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.configure( "org/hibernate/test/boot/cfgXml/hibernate.cfg.xml" )
				.build();
		try {
			final ConfigurationService cs = ssr.getService( ConfigurationService.class );
			// augmented form
			assertNotNull( cs.getSettings().get( "hibernate.cache.provider_class" ) );
			// original form
			assertNotNull( cs.getSettings().get( "cache.provider_class" ) );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
