	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::mapping-generated-UpdateTimestamp-persist-example[]
			Bid bid = new Bid();
			bid.setUpdatedBy( "John Doe" );
			bid.setCents( 150 * 100L );
			entityManager.persist( bid );
			//end::mapping-generated-UpdateTimestamp-persist-example[]
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::mapping-generated-UpdateTimestamp-update-example[]
			Bid bid = entityManager.find( Bid.class, 1L );

			bid.setUpdatedBy( "John Doe Jr." );
			bid.setCents( 160 * 100L );
			entityManager.persist( bid );
			//end::mapping-generated-UpdateTimestamp-update-example[]
		} );
	}
