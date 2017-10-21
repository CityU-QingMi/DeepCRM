	@Override
	public boolean next() {
		try {
			final boolean result = getResultSet().next();
			prepareCurrentRow( result );
			return result;
		}
		catch (SQLException sqle) {
			throw convert( sqle, "could not advance using next()" );
		}
	}
