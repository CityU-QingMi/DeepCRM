	@Override
	public boolean last() {
		try {
			final boolean result = getResultSet().last();
			prepareCurrentRow( result );
			return result;
		}
		catch (SQLException sqle) {
			throw convert( sqle, "could not advance using last()" );
		}
	}
