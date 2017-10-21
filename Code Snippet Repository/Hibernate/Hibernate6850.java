	@After
	public void tearDow() {
		doInHibernate( this::sessionFactory, session -> {
			session.doWork( connection -> {
				connection.createStatement().execute( "drop table TSHOW_SHOWDESCRIPTION" );
				connection.createStatement().execute( "drop table SHOW_DESCRIPTION" );
				connection.createStatement().execute( "drop table T_SHOW" );

			} );
		} );
	}
