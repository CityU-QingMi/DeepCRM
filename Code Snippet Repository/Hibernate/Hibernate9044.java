	@Test
	public void testFunctionWithJDBCByName() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			try {
				Session session = entityManager.unwrap( Session.class );
				Long phoneCount = session.doReturningWork( connection -> {
					CallableStatement function = null;
					try {
						function = connection.prepareCall( "{ ? = call sp_count_phones(?) }" );
						function.registerOutParameter( "phoneCount", Types.BIGINT );
						function.setLong( "personId", 1L );
						function.execute();
						return function.getLong( 1 );
					}
					finally {
						if ( function != null ) {
							function.close();
						}
					}
				} );
				assertEquals( Long.valueOf( 2 ), phoneCount );
			} catch (Exception e) {
				assertEquals( SQLFeatureNotSupportedException.class, e.getCause().getClass() );
			}
		} );
	}
