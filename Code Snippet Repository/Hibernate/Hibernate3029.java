	@Override
	public void beforeFirst() {
		try {
			getResultSet().beforeFirst();
		}
		catch (SQLException e) {
			throw getSession().getFactory().getSQLExceptionHelper().convert(
					e,
					"exception calling beforeFirst()"
			);
		}
		currentRow = null;
		currentPosition = 0;
	}
