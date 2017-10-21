	@Test
	@RequiresDialect(H2Dialect.class)
	@RequiresDialect(PostgreSQL81Dialect.class)
	@RequiresDialect(MySQL5Dialect.class)
	public void test_hql_group_by_example_4() {

		doInJPA( this::entityManagerFactory, entityManager -> {

			Call call11 = new Call();
			call11.setDuration( 10 );
			call11.setTimestamp( Timestamp.from( LocalDateTime.of( 2000, 1, 1, 0, 0, 0 ).toInstant( ZoneOffset.UTC ) ) );

			Phone phone = entityManager.createQuery( "select p from Phone p where p.calls is empty ", Phone.class).getResultList().get( 0 );

			phone.addCall(call11);
			entityManager.flush();
			entityManager.clear();

			List<Object[]> personTotalCallDurations = entityManager.createQuery(
				"select p, sum( c.duration ) " +
				"from Call c " +
				"join c.phone p " +
				"group by p", Object[].class )
			.getResultList();
			assertEquals(2, personTotalCallDurations.size());
		});
	}
