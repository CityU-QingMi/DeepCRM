	@Test
	public void testFlushAutoSQL() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			entityManager.createNativeQuery( "delete from Person" ).executeUpdate();;
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			log.info( "testFlushAutoSQL" );
			//tag::flushing-auto-flush-sql-example[]
			assertTrue(((Number) entityManager
					.createNativeQuery( "select count(*) from Person")
					.getSingleResult()).intValue() == 0 );

			Person person = new Person( "John Doe" );
			entityManager.persist( person );

			assertTrue(((Number) entityManager
					.createNativeQuery( "select count(*) from Person")
					.getSingleResult()).intValue() == 1 );
			//end::flushing-auto-flush-sql-example[]
		} );
	}
