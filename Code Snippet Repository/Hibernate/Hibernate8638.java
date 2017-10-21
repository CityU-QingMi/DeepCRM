	@Test
	public void testParentChild() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Parent p = new Parent();
		Child c = new Child();
		c.setParent(p);
		p.setChild(c);
		s.save(p);
		s.save(c);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		c = (Child) s.load( Child.class, new Long( c.getId() ) );
		p = c.getParent();
		assertTrue( "1-1 parent", p!=null );
		c.setCount(32);
		p.setCount(66);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		c = (Child) s.load( Child.class, new Long( c.getId() ) );
		p = c.getParent();
		assertTrue( "1-1 update", p.getCount()==66 );
		assertTrue( "1-1 update", c.getCount()==32 );
		assertTrue(
			"1-1 query",
				s.createQuery( "from Child c where c.parent.count=66" ).list().size()==1
		);
		assertTrue(
			"1-1 query",
			( (Object[]) s.createQuery( "from Parent p join p.child c where p.count=66" ).list().get(0) ).length==2
		);
		s.createQuery( "select c, c.parent from Child c order by c.parent.count" ).list();
		s.createQuery( "select c, c.parent from Child c where c.parent.count=66 order by c.parent.count" ).list();
		s.createQuery( "select c, c.parent, c.parent.count from Child c order by c.parent.count" ).iterate();
		List result = s.createQuery( "FROM Parent AS p WHERE p.count = ?" )
				.setParameter( 0, new Integer(66), StandardBasicTypes.INTEGER )
				.list();
		assertEquals( "1-1 query", 1, result.size() );
		s.delete(c); s.delete(p);
		t.commit();
		s.close();
	}
