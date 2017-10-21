	@Test
	@TestForIssue(jiraKey = "")
	public void testHqlQuery() {
		doInHibernate( this::sessionFactory, session -> {
						   final Query query = session.createQuery( "from TestEntity" );

						   final List<TestEntity> results = query.list();

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
