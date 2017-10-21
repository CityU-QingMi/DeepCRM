	@Before
	public void setUp() {
		doInHibernate( this::sessionFactory, session -> {
			ArticleTrading articleTrading = new ArticleTrading();
			articleTrading.setClassifier( "no_classification" );
			articleTrading.setPartyId( 2 );
			articleTrading.setDeletionTimestamp( Timestamp.valueOf( "9999-12-31 00:00:00" ) );
			articleTrading.setDeleted( true );

			ArticleRevision revision = new ArticleRevision();
			revision.addArticleTradings( articleTrading );
			revision.setDeletionTimestamp( Timestamp.valueOf( "9999-12-31 00:00:00" ) );
			revision.setDeleted( true );
			session.save( revision );

		} );
	}
