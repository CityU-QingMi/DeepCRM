	@Test
	public void testCriteriaThroughCompositeId() throws Exception {
		Session session = openSession();

		Criteria listActionRoles = session.createCriteria( ListActionRole.class );
		listActionRoles.add( Restrictions.eq( "isDeleted", false ) );

		Criteria roles = listActionRoles.createCriteria( "role" );
		roles.add( Restrictions.eq( "isDeleted", false ) );

		assertEquals(
				Arrays.asList( new ListActionRole[] { } ),
				listActionRoles.list()
		);

		session.close();
	}
