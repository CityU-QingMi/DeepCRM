	@Test
	public void testBatching() throws SQLException {
		doInHibernate( this::sessionFactory, session -> {
			Parent parent = new Parent();
			session.persist(parent);

			ChildA childA = new ChildA();
			childA.setParent(parent);
			session.persist(childA);

			ChildB childB = new ChildB();
			childB.setParent(parent);
			session.persist(childB);

			connectionProvider.clear();
		} );

		assertEquals( 3, connectionProvider.getPreparedStatements().size() );
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
	}
