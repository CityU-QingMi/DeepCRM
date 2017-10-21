	@Test
	public void testRemainsStoppedOnFailure() {
		final Properties properties = new Properties();
		properties.setProperty( JCacheRegionFactory.CONFIG_URI, "_fil:" );
		try {
			factory.start( null, properties );
			fail();
		}
		catch ( CacheException e ) {
			assertThat( factory.isStarted(), is( false ) );
		}

		properties.setProperty( JCacheRegionFactory.PROVIDER, "no.such.thing" );
		try {
			factory.start( null, properties );
			fail();
		}
		catch ( javax.cache.CacheException e ) {
			assertThat( factory.isStarted(), is( false ) );
		}
	}
