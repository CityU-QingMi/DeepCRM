	@Test
	public void testParentNullChild() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Parent p = new Parent();
		s.save(p);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		p = (Parent) s.load( Parent.class, new Long( p.getId() ) );
		assertTrue( p.getChild()==null );
		p.setCount(66);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		p = (Parent) s.load( Parent.class, new Long( p.getId() ) );
		assertTrue( "null 1-1 update", p.getCount()==66 );
		assertTrue( p.getChild()==null );
		s.delete(p);
		t.commit();
		s.close();
	}
