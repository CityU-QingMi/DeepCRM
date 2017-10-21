	@Test
	@TestForIssue(jiraKey = "")
	public void testGetWithUpdatedDetachedEntityInCompositeID() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Channel channel = new Channel();
		Presenter presenter = new Presenter();
		presenter.name = "Jane";
		TvMagazin tvMagazin = new TvMagazin();
		tvMagazin.id = new TvMagazinPk();
		tvMagazin.id.channel = channel;
		tvMagazin.id.presenter = presenter;
		s.persist( channel );
		s.persist( presenter );
		s.persist( tvMagazin );
		s.flush();

		s.clear();
		// update channel
		channel.name = "chnl";
		TvMagazinPk pkNew = new TvMagazinPk();
		// set pkNew.channel to the unmerged copy.
		pkNew.channel = channel;
		pkNew.presenter = presenter;
		// the following fails because there is already a managed channel
		tvMagazin = s.get( TvMagazin.class, pkNew );
		channel = s.get( Channel.class, channel.id );
		assertNull( channel.name );
		s.flush();
		s.clear();

		// make sure that channel.name is still null
		channel = s.get( Channel.class, channel.id );
		assertNull( channel.name );

		tx.rollback();
		s.close();
	}
