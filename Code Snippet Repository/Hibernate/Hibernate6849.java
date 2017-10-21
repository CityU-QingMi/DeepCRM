	@Before
	public void setUp() {
		doInHibernate( this::sessionFactory, session -> {
			session.doWork( connection -> {
				connection.createStatement().execute("create table SHOW_DESCRIPTION ( ID integer not null, primary key (ID) )" );
				connection.createStatement().execute("create table T_SHOW ( id integer not null, primary key (id) )" );
				connection.createStatement().execute("create table TSHOW_SHOWDESCRIPTION ( DESCRIPTION_ID integer, SHOW_ID integer not null, primary key (SHOW_ID) )" );

			} );
		} );

		doInHibernate( this::sessionFactory, session -> {
			Show show = new Show();
			show.setId( 1 );
			ShowDescription showDescription = new ShowDescription();
			showDescription.setId( 2 );
			show.setDescription( showDescription );
			session.save( showDescription );
			session.save( show );

		} );

		doInHibernate( this::sessionFactory, session -> {
			session.doWork( connection -> {
				connection.createStatement().execute( "delete from SHOW_DESCRIPTION where ID = 2" );

			} );
		} );
	}
