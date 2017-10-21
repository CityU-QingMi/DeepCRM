	@Test
	public void testFlushAutoSQLSynchronization() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			entityManager.createNativeQuery( "delete from Person" ).executeUpdate();;
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			log.info( "testFlushAutoSQLSynchronization" );
			//tag::flushing-auto-flush-sql-synchronization-example[]
			assertTrue(((Number) entityManager
					.createNativeQuery( "select count(*) from Person")
					.getSingleResult()).intValue() == 0 );

			Person person = new Person( "John Doe" );
			entityManager.persist( person );
			Session session = entityManager.unwrap( Session.class );

			assertTrue(((Number) session
					.createNativeQuery( "select count(*) from Person")
					.addSynchronizedEntityClass( Person.class )
					.uniqueResult()).intValue() == 1 );
			//end::flushing-auto-flush-sql-synchronization-example[]
		} );
	}
