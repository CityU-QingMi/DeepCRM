	@Test
	public void testQueryWithoutEnoughParams() {
		MappingSqlQuery<Integer> query = new MappingSqlQuery<Integer>() {
			@Override
			protected Integer mapRow(ResultSet rs, int rownum) throws SQLException {
				return rs.getInt(1);
			}
		};
		query.setDataSource(dataSource);
		query.setSql(SELECT_ID_WHERE);
		query.declareParameter(new SqlParameter(COLUMN_NAMES[0], COLUMN_TYPES[0]));
		query.declareParameter(new SqlParameter(COLUMN_NAMES[1], COLUMN_TYPES[1]));
		query.compile();

		thrown.expect(InvalidDataAccessApiUsageException.class);
		query.execute();
	}
