	@Test
	@TestForIssue( jiraKey = "" )
	public void testDisabledEnabled() {
		final Map<Object, Object> config = Environment.getProperties();
		config.put( org.hibernate.jpa.AvailableSettings.LOADED_CLASSES, Collections.singletonList( AnEntity.class ) );
		config.put( "javax.persistence.sharedCache.mode", SharedCacheMode.ENABLE_SELECTIVE );
		config.put( AvailableSettings.USE_SECOND_LEVEL_CACHE, "false" );

		testIt( config );

		config.put( AvailableSettings.USE_SECOND_LEVEL_CACHE, "true" );

		testIt( config );
	}
