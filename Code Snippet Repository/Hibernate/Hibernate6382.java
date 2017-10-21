	@Test
	@TestForIssue(jiraKey = "")
	public void testGetWithDetachedEntityInCompositeIDWithManagedCopy() throws Exception {
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
		// merge channel to put channel back in PersistenceContext
		s.merge( channel );
		TvMagazinPk pkNew = new TvMagazinPk();
		// set pkNew.channel to the unmerged copy.
		pkNew.channel = channel;
		pkNew.presenter = presenter;
		// the following fails because there is already a managed channel
		tvMagazin = s.get( TvMagazin.class, pkNew );
		tx.rollback();
		s.close();
	}
