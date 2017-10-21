	@Test
	public void testFlushAutoJPQL() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			log.info( "testFlushAutoJPQL" );
			//tag::flushing-auto-flush-jpql-example[]
			Person person = new Person( "John Doe" );
			entityManager.persist( person );
			entityManager.createQuery( "select p from Advertisement p" ).getResultList();
			entityManager.createQuery( "select p from Person p" ).getResultList();
			//end::flushing-auto-flush-jpql-example[]
		} );
	}
