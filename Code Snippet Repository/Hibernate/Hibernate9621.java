	@Test
	public void testUnconstrainedNoCache() {
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
		p = (Person) session.get(Person.class, "gavin");
		assertNull( p.getEmployee() );
		p.setEmployee( new Employee("123456") );
		tx.commit();
		session.close();

		sessionFactory().getCache().evictEntityRegion( Person.class );
		
		session = openSession();
		tx = session.beginTransaction();
		p = (Person) session.get(Person.class, "gavin");
		assertTrue( Hibernate.isInitialized( p.getEmployee() ) );
		assertNotNull( p.getEmployee() );
		session.delete(p);
		tx.commit();
		session.close();
	}
