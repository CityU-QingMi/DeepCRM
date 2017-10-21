	@Override
	@NonNull
	public DataAccessException translate(String task, @Nullable String sql, SQLException ex) {
		Assert.notNull(ex, "Cannot translate a null SQLException");

		DataAccessException dae = doTranslate(task, sql, ex);
		if (dae != null) {
			// Specific exception match found.
			return dae;
		}

		// Looking for a fallback...
		SQLExceptionTranslator fallback = getFallbackTranslator();
		if (fallback != null) {
			dae = fallback.translate(task, sql, ex);
			if (dae != null) {
				// Fallback exception match found.
				return dae;
			}
		}

		// We couldn't identify it more precisely.
		return new UncategorizedSQLException(task, sql, ex);
	}
