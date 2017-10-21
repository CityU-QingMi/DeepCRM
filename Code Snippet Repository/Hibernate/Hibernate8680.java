	@Test
	@FailureExpected( jiraKey = "" )
	public void testReturnPropertyComponentRename() throws HibernateException, SQLException {
		// failure expected because this was a regression introduced previously which needs to get tracked down.
		Componentizable componentizable = setupComponentData();
		
		Session session = openSession();
		session.beginTransaction();
		Query namedQuery = session.getNamedQuery("queryComponentWithOtherColumn");
		List list = namedQuery.list();
		
		assertEquals(1, list.size());
		assertEquals( "flakky comp", ( (Componentizable) list.get(0) ).getComponent().getName() );
		
		session.clear();
		session.delete(componentizable);
		session.getTransaction().commit();
		session.close();
	}
