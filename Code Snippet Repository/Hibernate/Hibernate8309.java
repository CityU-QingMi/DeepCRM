	@Test
	@TestForIssue(jiraKey = "")
	public void testLazyInitialization() {
		createTestData();
		doInHibernate( this::sessionFactory, session -> {
			Category category7 = session.find( Category.class, 7 );
			// Must be empty because although Post and Category share the same column for their category relations,
			// the children must be based on entities that are of type Category
			Assert.assertTrue( category7.children.isEmpty() );
		} );
	}
