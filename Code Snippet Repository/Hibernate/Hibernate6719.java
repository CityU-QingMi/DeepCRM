	@Test
	public void testManyToOneOnAbstract() throws Exception {
		Folder f = new Folder();
		f.setName( "data" );
		ProgramExecution remove = new ProgramExecution();
		remove.setAction( "remove" );
		remove.setAppliesOn( f );
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		s.persist( f );
		s.persist( remove );
		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		remove = (ProgramExecution) s.get( ProgramExecution.class, remove.getId() );
		assertNotNull( remove );
		assertNotNull( remove.getAppliesOn().getName() );
		s.delete( remove );
		s.delete( remove.getAppliesOn() );
		tx.commit();
		s.close();
	}
