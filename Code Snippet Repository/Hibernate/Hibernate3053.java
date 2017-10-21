	@Override
	public boolean previous() {
		try {
			final boolean result = getResultSet().previous();
			prepareCurrentRow( result );
			return result;
		}
		catch (SQLException sqle) {
			throw convert( sqle, "could not advance using previous()" );
		}
	}
