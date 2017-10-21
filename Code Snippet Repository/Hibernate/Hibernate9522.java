	@Test
	public void testIndexRelatedFunctions() {
		Session session = openSession();
		session.beginTransaction();
		session.createQuery( "from Employee e join e.managerBySite as m where index(m) is not null" )
				.list();
		session.createQuery( "from Employee e join e.managerBySite as m where minIndex(m) is not null" )
				.list();
		session.createQuery( "from Employee e join e.managerBySite as m where maxIndex(m) is not null" )
				.list();
		session.getTransaction().commit();
		session.close();
	}
