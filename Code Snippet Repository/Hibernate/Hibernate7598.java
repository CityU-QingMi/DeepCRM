	@Test
	public void testTruncateAndTruncFunctions(){
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person();
			person.setId( 1L );
			person.setHighestScore( 99.56d );
			entityManager.persist( person );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Double score = entityManager.createQuery(
				"select truncate(p.highestScore, 1) " +
				"from Person p " +
				"where p.id = :id", Double.class)
			.setParameter( "id", 1L )
			.getSingleResult();

			assertEquals( 99.5d, score, 0.01 );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Double score = entityManager.createQuery(
				"select trunc(p.highestScore, 1) " +
				"from Person p " +
				"where p.id = :id", Double.class)
			.setParameter( "id", 1L )
			.getSingleResult();

			assertEquals( 99.5d, score, 0.01 );
		} );
		
	}
