		public StoredProcedureExceptionTranslator(DataSource ds) {
			setDataSource(ds);
			setSql(SQL);
			getJdbcTemplate().setExceptionTranslator(new SQLExceptionTranslator() {
				@Override
				public DataAccessException translate(String task, @Nullable String sql, SQLException ex) {
					return new CustomDataException(sql, ex);
				}
			});
			compile();
		}
