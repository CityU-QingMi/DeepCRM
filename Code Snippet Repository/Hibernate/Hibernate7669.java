	@Test
	public void testQuerySubclassAttribute() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Person p = new Person();
		p.setId( 5 );
		p.setName("Emmanuel");
		p.setSex('M');
		s.save( p );
		Employee q = new Employee();
		q.setId( 6 );
		q.setName("Steve");
		q.setSex('M');
		q.setTitle("Mr");
		q.setSalary( new BigDecimal(1000) );
		s.save( q );

		List result = s.createQuery("from org.hibernate.test.discriminator.Person where salary > 100").list();
		assertEquals( result.size(), 1 );
		assertSame( result.get(0), q );

		result = s.createQuery("from org.hibernate.test.discriminator.Person where salary > 100 or name like 'E%'").list();
		assertEquals( result.size(), 2 );

		result = s.createCriteria(Person.class)
			.add( Property.forName("salary").gt( new BigDecimal(100) ) )
			.list();
		assertEquals( result.size(), 1 );
		assertSame( result.get(0), q );

		//TODO: make this work:
/**/
/**/
/**/

		s.delete(p);
		s.delete(q);
		t.commit();
		s.close();
	}
