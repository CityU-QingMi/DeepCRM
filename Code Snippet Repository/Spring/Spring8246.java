	public static int deleteFromTableWhere(
			JdbcTemplate jdbcTemplate, String tableName, String whereClause, Object... args) {

		String sql = "DELETE FROM " + tableName;
		if (StringUtils.hasText(whereClause)) {
			sql += " WHERE " + whereClause;
		}
		int rowCount = (args.length > 0 ? jdbcTemplate.update(sql, args) : jdbcTemplate.update(sql));
		if (logger.isInfoEnabled()) {
			logger.info("Deleted " + rowCount + " rows from table " + tableName);
		}
		return rowCount;
	}
