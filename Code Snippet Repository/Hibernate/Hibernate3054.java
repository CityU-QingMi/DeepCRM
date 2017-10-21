	@Override
	public boolean setRowNumber(int rowNumber) throws HibernateException {
		if ( rowNumber >= 0 ) {
			rowNumber++;
		}

		try {
			final boolean result = getResultSet().absolute( rowNumber );
			prepareCurrentRow( result );
			return result;
		}
		catch (SQLException sqle) {
			throw convert( sqle, "could not advance using absolute()" );
		}
	}
