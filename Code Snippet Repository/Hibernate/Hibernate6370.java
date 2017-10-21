	@Test
	public void testSecondaryTableWithIdClass() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Channel channel = new Channel();
		s.persist( channel );
		Presenter pres = new Presenter();
		pres.name = "Bob";
		s.persist( pres );
		TvProgramIdClass program = new TvProgramIdClass();
		program.time = new Date();
		program.channel = channel;
		program.presenter = pres;
		program.text = "Jump the shark programming";
		s.persist( program );
		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		program = (TvProgramIdClass) s.createQuery( "from TvProgramIdClass pr" ).uniqueResult();
		assertNotNull( program.channel );
		assertEquals( channel.id, program.channel.id );
		assertNotNull( program.presenter );
		assertNotNull( program.text );
		assertEquals( pres.name, program.presenter.name );
		s.delete( program );
		s.delete( program.channel );
		s.delete( program.presenter );
		tx.commit();
		s.close();
	}
