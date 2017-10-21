	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::associations-many-to-many-bidirectional-lifecycle-example[]
			Person person1 = new Person( "ABC-123" );
			Person person2 = new Person( "DEF-456" );

			Address address1 = new Address( "12th Avenue", "12A", "4005A" );
			Address address2 = new Address( "18th Avenue", "18B", "4007B" );

			person1.addAddress( address1 );
			person1.addAddress( address2 );

			person2.addAddress( address1 );

			entityManager.persist( person1 );
			entityManager.persist( person2 );

			entityManager.flush();

			person1.removeAddress( address1 );
			//end::associations-many-to-many-bidirectional-lifecycle-example[]
		} );
	}
