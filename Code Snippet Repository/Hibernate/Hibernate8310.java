	@Test
	@TestForIssue(jiraKey = "")
	public void testJoinFetch() {
		createTestData();
		doInHibernate( this::sessionFactory, session -> {
			Category category7 = session.createQuery(
					"SELECT c FROM " + Category.class.getName() + " c LEFT JOIN FETCH c.children WHERE c.id = :id",
					Category.class
			)
					.setParameter( "id", 7 )
					.getSingleResult();
			// Must be empty because although Post and Category share the same column for their category relations,
			// the children must be based on entities that are of type Category
			Assert.assertTrue( category7.children.isEmpty() );
		} );
	}
