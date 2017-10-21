	@Test
	public void testFlushAutoJPQLOverlap() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			log.info( "testFlushAutoJPQLOverlap" );
			//tag::flushing-auto-flush-jpql-overlap-example[]
			Person person = new Person( "John Doe" );
			entityManager.persist( person );
			entityManager.createQuery( "select p from Person p" ).getResultList();
			//end::flushing-auto-flush-jpql-overlap-example[]
		} );
	}
