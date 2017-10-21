	@Test
	public void test_hql_order_by_example_2() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-order-by-example[]

			List<Object[]> personTotalCallDurations = entityManager.createQuery(
				"select p.name, sum( c.duration ) as total " +
				"from Call c " +
				"join c.phone ph " +
				"join ph.person p " +
				"group by p.name " +
				"order by total", Object[].class )
			.getResultList();
			//end::hql-order-by-example[]
			assertEquals(1, personTotalCallDurations.size());
		});
	}
