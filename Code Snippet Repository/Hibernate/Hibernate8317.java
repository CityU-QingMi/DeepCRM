	@Test
	public void shouldNotRetrieveSubSubSubEntityWithHQL() {
		session = openSession();
		try {
			SubSubSubEntity loaded = (SubSubSubEntity) session.createQuery(
					"select se from SubSubSubEntity se where se.id = :id" )
					.setLong( "id", subSubEntityId )
					.uniqueResult();
			assertNull( loaded );
		}
		finally {
			session.close();
		}
	}
