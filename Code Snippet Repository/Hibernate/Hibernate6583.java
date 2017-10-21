	@Test
	public void testFilter() {
		try (Session session = openSession()) {
			Assert.assertEquals(
					Long.valueOf( 4 ),
					session.createQuery( "select count(u) from User u" ).uniqueResult()
			);
			session.enableFilter( "ageFilter" ).setParameter( "age", 24 );
			Assert.assertEquals(
					Long.valueOf( 2 ),
					session.createQuery( "select count(u) from User u" ).uniqueResult()
			);
		}
	}
