	@Test
	public void shouldRetrieveSubEntity() {
		session = openSession();
		try {
			RootEntity loaded = session.get( SubEntity.class, subSubEntityId );
			assertNotNull( loaded );
			assertTrue( loaded instanceof SubSubEntity );
		}
		finally {
			session.close();
		}
	}
