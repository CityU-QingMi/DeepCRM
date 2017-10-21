	@Test
	public void testFunctionWithJDBC() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			Long phoneCount = session.doReturningWork( connection -> {
				CallableStatement function = null;
				try {
					function = connection.prepareCall( "{ ? = call sp_count_phones(?) }" );
					function.registerOutParameter( 1, Types.BIGINT );
					function.setLong( 2, 1L );
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
		} );
	}
