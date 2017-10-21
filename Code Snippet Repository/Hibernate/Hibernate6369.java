	@Test
	public void testSecondaryTableWithCompositeId() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Channel channel = new Channel();
		s.persist( channel );
		Presenter pres = new Presenter();
		pres.name = "Tim Russet";
		s.persist( pres );
		TvMagazinPk pk = new TvMagazinPk();
		TvProgram program = new TvProgram();
		program.time = new Date();
		program.id = pk;
		program.text = "Award Winning Programming";
		pk.channel = channel;
		pk.presenter = pres;
		s.persist( program );
		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		program = (TvProgram) s.createQuery( "from TvProgram pr" ).uniqueResult();
		assertNotNull( program.id );
		assertNotNull( program.id.channel );
		assertEquals( channel.id, program.id.channel.id );
		assertNotNull( program.id.presenter );
		assertNotNull( program.text );
		assertEquals( pres.name, program.id.presenter.name );
		s.delete( program );
		s.delete( program.id.channel );
		s.delete( program.id.presenter );
		tx.commit();
		s.close();
	}
