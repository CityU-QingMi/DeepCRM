	@Test
	public void testBadClobDataSavedAsStringworksAfterUpdate() {
		doInHibernate( this::sessionFactory, session -> {

						   session.doWork( connection -> {
											   try(Statement statement = connection.createStatement()) {
												   statement.executeUpdate(
														   "update test_entity\n" +
																   "set \n" +
																   "    clobfield = lo_from_bytea(0, cast(clobfield as bytea)),\n" +
																   "    firstlobfield = lo_from_bytea(0, cast(firstlobfield as bytea)),\n" +
																   "    secondlobfield = lo_from_bytea(0, cast(secondlobfield as bytea))"
												   );
											   }
										   } );
					   } );

		doInHibernate( this::sessionFactory, session -> {
						   final Query query = session.createQuery( "from TestEntity" );

						   final List<TestEntity> results = query.list();

						   assertThat( results.size(), is( 1 ) );

						   final TestEntity testEntity = results.get( 0 );
						   assertThat( testEntity.getFirstLobField(), is( value1 ) );
						   assertThat( testEntity.getSecondLobField(), is( value2 ) );
						   final Clob clobField = testEntity.getClobField();
						   try {

							   assertThat( clobField.getSubString( 1, (int) clobField.length() ), is( value3 ) );
						   }
						   catch (SQLException e) {
							   fail( e.getMessage() );
						   }
					   } );
	}
