	@Override
	public boolean next() {
		if ( maxPosition != null && maxPosition <= currentPosition ) {
			currentRow = null;
			currentPosition = maxPosition + 1;
			return false;
		}

		if ( isResultSetEmpty() ) {
			currentRow = null;
			currentPosition = 0;
			return false;
		}

		final Object row = getLoader().loadSequentialRowsForward(
				getResultSet(),
				getSession(),
				getQueryParameters(),
				true
		);


		final boolean afterLast;
		try {
			afterLast = getResultSet().isAfterLast();
		}
		catch (SQLException e) {
			throw getSession().getFactory().getSQLExceptionHelper().convert(
					e,
					"exception calling isAfterLast()"
			);
		}

		currentPosition++;
		currentRow = new Object[] {row};

		if ( afterLast ) {
			if ( maxPosition == null ) {
				// we just hit the last position
				maxPosition = currentPosition;
			}
		}

		afterScrollOperation();

		return true;
	}
