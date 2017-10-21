	@Test
	@TestForIssue(jiraKey = "")
	public void testUsingLobPropertyInNativeQuery() {
		doInHibernate( this::sessionFactory, session -> {
						   final List<String> results = session.createNativeQuery(
								   "select convert_from(lo_get(cast(te.secondLobField as oid)), 'UTF8') " +
										   "from test_entity te " +
										   "where lower(convert_from(lo_get(cast(te.clobField as oid)), 'UTF8')) LIKE :value" )
								   .setParameter( "value", value2 )
								   .list();

						   assertThat( results.size(), is( 1 ) );

						   assertThat( results.get( 0 ), is( value2 ) );
					   } );
	}
