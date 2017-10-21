	@Test
	public void shouldNotRetrieveSubSubSubEntityWithCriteria() {
		session = openSession();
		try {
			SubSubSubEntity loaded = (SubSubSubEntity) session.createCriteria( SubSubSubEntity.class )
					.add( Restrictions.idEq( subSubEntityId ) )
					.uniqueResult();
			assertNull( loaded );
		}
		finally {
			session.close();
		}
	}
