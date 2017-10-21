	@Test
	public void testLockingJoinedSubclass() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Person p = new Person();
		p.setName("Emmanuel");
		p.setSex('M');
		s.persist(p);
		Employee q = new Employee();
		q.setName("Steve");
		q.setSex('M');
		q.setTitle("Mr");
		q.setSalary( new BigDecimal(1000) );
		s.persist(q);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		s.lock( p, LockMode.UPGRADE );
		s.lock( q, LockMode.UPGRADE );
		s.delete( p );
		s.delete( q );
		t.commit();
		s.close();
	}
