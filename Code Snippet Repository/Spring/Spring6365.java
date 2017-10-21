	@Test
	public void testMappingWithUnpopulatedFieldsNotChecked() throws Exception {
		Mock mock = new Mock();
		List<ExtendedPerson> result = mock.getJdbcTemplate().query(
				"select name, age, birth_date, balance from people",
				new BeanPropertyRowMapper<>(ExtendedPerson.class));
		assertEquals(1, result.size());
		ExtendedPerson bean = result.get(0);
		verifyPerson(bean);
		mock.verifyClosed();
	}
