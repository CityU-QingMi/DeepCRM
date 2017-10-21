	@Test
	@TestForIssue(jiraKey = "")
	public void testLeftJoinMapAndUseKeyExpression() {
		doInHibernate( this::sessionFactory, s -> {
			// Assert that a left join is used for joining the map key entity table
			List keyValues= s.createQuery( "select key(v) from BaseTestEntity bte left join bte.entities te left join te.values v" ).list();
			System.out.println( keyValues );
			assertEquals( 2, keyValues.size() );
		} );
	}
