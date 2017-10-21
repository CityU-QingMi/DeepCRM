	@Override
	public boolean first() {
		try {
			final boolean result = getResultSet().first();
			prepareCurrentRow( result );
			return result;
		}
		catch (SQLException sqle) {
			throw convert( sqle, "could not advance using first()" );
		}
	}
