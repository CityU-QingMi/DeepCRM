	@Test
	public void shouldRetrieveSubSubEntityWithHQL() {
		session = openSession();
		try {
			SubSubEntity loaded = (SubSubEntity) session.createQuery(
					"select se from SubSubEntity se where se.id = :id" )
					.setLong( "id", subSubEntityId )
					.uniqueResult();
			assertNotNull( loaded );
		}
		finally {
			session.close();
		}
	}
