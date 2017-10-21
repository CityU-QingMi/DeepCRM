	@Test
	@Transactional
	public void transactionalTest() {
		TransactionTestUtils.assertInTransaction(true);

		ClassPathResource resource = new ClassPathResource("/org/springframework/test/context/jdbc/data.sql");
		new ResourceDatabasePopulator(resource).execute(jdbcTemplate.getDataSource());

		assertNumUsers(1);
	}
