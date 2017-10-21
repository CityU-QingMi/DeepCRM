	@Test
	public void testFlushAutoSQLNativeSession() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			entityManager.createNativeQuery( "delete from Person" ).executeUpdate();;
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			log.info( "testFlushAutoSQLNativeSession" );
			//tag::flushing-auto-flush-sql-native-example[]
			assertTrue(((Number) entityManager
					.createNativeQuery( "select count(*) from Person")
					.getSingleResult()).intValue() == 0 );

			Person person = new Person( "John Doe" );
			entityManager.persist( person );
			Session session = entityManager.unwrap(Session.class);

			// for this to work, the Session/EntityManager must be put into COMMIT FlushMode
			//  - this is a change since 5.2 to account for merging EntityManager functionality
			// 		directly into Session.  Flushing would be the JPA-spec compliant behavior,
			//		so we know do that by default.
			session.setFlushMode( FlushModeType.COMMIT );
			//		or using Hibernate's FlushMode enum
			//session.setHibernateFlushMode( FlushMode.COMMIT );

			assertTrue(((Number) session
					.createNativeQuery( "select count(*) from Person")
					.uniqueResult()).intValue() == 0 );
			//end::flushing-auto-flush-sql-native-example[\]
		} );
	}
