	@Test
	public void testConstraint() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::associations-one-to-one-bidirectional-lifecycle-example[]
			Phone phone = new Phone( "123-456-7890" );
			PhoneDetails details = new PhoneDetails( "T-Mobile", "GSM" );

			phone.addDetails( details );
			entityManager.persist( phone );
			//end::associations-one-to-one-bidirectional-lifecycle-example[]
		} );
		try {
			doInJPA( this::entityManagerFactory, entityManager -> {

				Phone phone = entityManager.find( Phone.class, 1L );

				//tag::associations-one-to-one-bidirectional-constraint-example[]
				PhoneDetails otherDetails = new PhoneDetails( "T-Mobile", "CDMA" );
				otherDetails.setPhone( phone );
				entityManager.persist( otherDetails );
				entityManager.flush();
				entityManager.clear();

				//throws javax.persistence.PersistenceException: org.hibernate.HibernateException: More than one row with the given identifier was found: 1
				phone = entityManager.find( Phone.class, phone.getId() );
				//end::associations-one-to-one-bidirectional-constraint-example[]
				phone.getDetails().getProvider();
			} );
			Assert.fail( "Expected: HHH000327: Error performing load command : org.hibernate.HibernateException: More than one row with the given identifier was found: 1" );
		}
		catch (Exception expected) {
			log.error( "Expected", expected );
		}
	}
