	private boolean isResultSetEmpty() {
		try {
			return currentPosition == 0 && !getResultSet().isBeforeFirst() && !getResultSet().isAfterLast();
		}
		catch (SQLException e) {
			throw getSession().getFactory().getSQLExceptionHelper().convert(
					e,
					"Could not determine if resultset is empty due to exception calling isBeforeFirst or isAfterLast()"
			);
		}
	}
