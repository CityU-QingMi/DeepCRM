	@Test
	public void testGetProperties() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		Map<String, Object> properties = em.getProperties();
		assertNotNull( properties );
		try {
			properties.put( "foo", "bar" );
			fail();
		}
		catch ( UnsupportedOperationException e ) {
			// success
		}

		assertTrue( properties.containsKey( AvailableSettings.FLUSH_MODE ) );
	}
