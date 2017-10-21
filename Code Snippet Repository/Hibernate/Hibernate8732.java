	@Test
	@TestForIssue(jiraKey = "")
	public void testSelectClobPropertyInNativeQuery() {
		doInHibernate( this::sessionFactory, session -> {
						   final List<byte[]> results = session.createNativeQuery(
								   "select lo_get(cast(te.clobField as oid)) " +
										   "from test_entity te " +
										   "where lower(convert_from(lo_get(cast(te.clobField as oid)), 'UTF8')) LIKE :value" )
								   .setParameter( "value", value2 )
								   .list();

						   assertThat( results.size(), is( 1 ) );

						   try {
							   assertThat( new String( results.get( 0 ), "UTF8"), is( value2 ) );
						   }
						   catch (UnsupportedEncodingException e) {
							   fail(e.getMessage());
						   }
					   } );
	}
