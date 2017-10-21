	@Test
	public void testMappingWithInheritance() throws Exception {
		Mock mock = new Mock();
		List<ConcretePerson> result = mock.getJdbcTemplate().query(
				"select name, age, birth_date, balance from people",
				new BeanPropertyRowMapper<>(ConcretePerson.class));
		assertEquals(1, result.size());
		verifyPerson(result.get(0));
		mock.verifyClosed();
	}
