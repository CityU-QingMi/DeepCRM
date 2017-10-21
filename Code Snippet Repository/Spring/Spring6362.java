	@Test
	public void testStaticQueryWithRowMapper() throws Exception {
		Mock mock = new Mock();
		List<Person> result = mock.getJdbcTemplate().query(
				"select name, age, birth_date, balance from people",
				new BeanPropertyRowMapper<>(Person.class));
		assertEquals(1, result.size());
		verifyPerson(result.get(0));
		mock.verifyClosed();
	}
