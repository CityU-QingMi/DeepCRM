	@Test
	public void shouldRetrieveSubSubEntityWithCriteria() {
		session = openSession();
		try {
			SubSubEntity loaded = (SubSubEntity) session.createCriteria( SubSubEntity.class )
					.add( Restrictions.idEq( subSubEntityId ) )
					.uniqueResult();
			assertNotNull( loaded );
		}
		finally {
			session.close();
		}
	}
