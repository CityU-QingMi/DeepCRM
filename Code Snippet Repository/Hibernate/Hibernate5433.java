	@Test
	@TestForIssue(jiraKey = "")
	public void shouldMaintainExtraStateWhenUsingIdentityIdGenerationStrategy() {
		session = openSession();
		session.getTransaction().begin();

		ChineseTakeawayRestaurant mrKim = new ChineseTakeawayRestaurant();
		mrKim.setGobelinStars( 3 );

		// As a side-effect, the id setter will populate the test extra state
		session.persist( mrKim );

		session.getTransaction().commit();

		TestExtraState extraState = getEntityEntry( mrKim ).getExtraState( TestExtraState.class );
		assertNotNull( "Test extra state was not propagated from temporary to final entity entry", extraState );
		assertEquals( 311, extraState.getValue() );

		session.close();
	}
