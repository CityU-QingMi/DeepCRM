	@Test
	@RequiresDialect(H2Dialect.class)
	@RequiresDialect(Oracle8iDialect.class)
	@RequiresDialect(MySQL5Dialect.class)
	public void test_hql_bit_length_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-bit-length-function-example[]
			List<Number> bits = entityManager.createQuery(
				"select bit_length( c.duration ) " +
				"from Call c ", Number.class )
			.getResultList();
			//end::hql-bit-length-function-example[]
			assertEquals(2, bits.size());
		});
	}
