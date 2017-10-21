	@Test
	public void testClassWhere() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Baz baz = new Baz();
		baz.setParts( new ArrayList() );
		Part p1 = new Part();
		p1.setDescription("xyz");
		Part p2 = new Part();
		p2.setDescription("abc");
		baz.getParts().add(p1);
		baz.getParts().add(p2);
		s.save(baz);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		assertTrue( s.createCriteria(Part.class).list().size()==1 ); //there is a where condition on Part mapping
		assertTrue( s.createCriteria(Part.class).add( Restrictions.eq( "id", p1.getId() ) ).list().size()==1 );
		assertTrue( s.createQuery("from Part").list().size()==1 );
		assertTrue( s.createQuery("from Baz baz join baz.parts").list().size()==2 );
		baz = (Baz) s.createCriteria(Baz.class).uniqueResult();
		assertTrue( s.createFilter( baz.getParts(), "" ).list().size()==2 );
		//assertTrue( baz.getParts().size()==1 );
		s.delete( s.get( Part.class, p1.getId() ));
		s.delete( s.get( Part.class, p2.getId() ));
		s.delete(baz);
		t.commit();
		s.close();
	}
