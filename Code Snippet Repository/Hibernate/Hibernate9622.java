	@Test
	public void testUnconstrainedOuterJoinFetch() {
		Session session = openSession();
		Transaction tx = session.beginTransaction();
		Person p = new Person("gavin");
		p.setEmployeeId("123456");
		session.persist(p);
		tx.commit();
		session.close();
		
		sessionFactory().getCache().evictEntityRegion( Person.class );
		
		session = openSession();
		tx = session.beginTransaction();
		p = (Person) session.createCriteria(Person.class)
			.setFetchMode("employee", FetchMode.JOIN)
			.add( Restrictions.idEq("gavin") )
			.uniqueResult();
		assertNull( p.getEmployee() );
		p.setEmployee( new Employee("123456") );
		tx.commit();
		session.close();

		sessionFactory().getCache().evictEntityRegion( Person.class );
		
		session = openSession();
		tx = session.beginTransaction();
		p = (Person) session.createCriteria(Person.class)
			.setFetchMode("employee", FetchMode.JOIN)
			.add( Restrictions.idEq("gavin") )
			.uniqueResult();
		assertTrue( Hibernate.isInitialized( p.getEmployee() ) );
		assertNotNull( p.getEmployee() );
		session.delete(p);
		tx.commit();
		session.close();
	}
