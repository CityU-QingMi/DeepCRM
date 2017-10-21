	@Test
	@TestForIssue(jiraKey = "")
	public void testUsingStringLobAnnotatedPropertyInNativeQuery() {
		doInHibernate( this::sessionFactory, session -> {
						   final List<TestEntity> results = session.createNativeQuery(
								   "select te.* " +
										   "from test_entity te " +
										   "where lower(convert_from(lo_get(cast(te.firstLobField as oid)), 'UTF8')) LIKE :value", TestEntity.class )
								   .setParameter( "value", value1 )
								   .getResultList();

						   assertThat( results.size(), is( 1 ) );

						   final TestEntity testEntity = results.get( 0 );
						   assertThat( testEntity.getFirstLobField(), is( value1 ) );
						   assertThat( testEntity.getSecondLobField(), is( value2 ) );
						   final Clob clobField = testEntity.getClobField();
						   try {
							   assertThat( clobField.getSubString( 1, (int) clobField.length() ), is( value2 ) );
						   }
						   catch (SQLException e) {
							   fail( e.getMessage() );
						   }
					   } );
	}
