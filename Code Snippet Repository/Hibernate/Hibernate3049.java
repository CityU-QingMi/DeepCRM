	@Override
	public boolean scroll(int i) {
		try {
			final boolean result = getResultSet().relative( i );
			prepareCurrentRow( result );
			return result;
		}
		catch (SQLException sqle) {
			throw convert( sqle, "could not advance using scroll()" );
		}
	}
