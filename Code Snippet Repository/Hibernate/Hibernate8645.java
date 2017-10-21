	@Test
	public void testQueryOneToOne() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Serializable id = s.save( new Parent() );
		assertTrue( s.createQuery( "from Parent p left join fetch p.child" ).list().size()==1 );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		Parent p = (Parent) s.createQuery("from Parent p left join fetch p.child").uniqueResult();
		assertTrue( p.getChild()==null );
		s.createQuery( "from Parent p join p.child c where c.x > 0" ).list();
		s.createQuery( "from Child c join c.parent p where p.x > 0" ).list();
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		s.delete( s.get(Parent.class, id) );
		t.commit();
		s.close();
	}
