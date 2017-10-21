	@Override
	public boolean last() {
		boolean more = false;
		if ( maxPosition != null ) {
			if ( currentPosition > maxPosition ) {
				more = previous();
			}
			for ( int i = currentPosition; i < maxPosition; i++ ) {
				more = next();
			}
		}
		else {
			try {
				if ( isResultSetEmpty() || getResultSet().isAfterLast() ) {
					// should not be able to reach last without maxPosition being set
					// unless there are no results
					return false;
				}

				while ( !getResultSet().isAfterLast() ) {
					more = next();
				}
			}
			catch (SQLException e) {
				throw getSession().getFactory().getSQLExceptionHelper().convert(
						e,
						"exception calling isAfterLast()"
				);
			}
		}

		afterScrollOperation();

		return more;
	}
