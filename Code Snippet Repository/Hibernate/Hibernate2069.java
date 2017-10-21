	protected void resetIfNeeded() throws SQLException {
		try {
			if ( needsReset ) {
				characterStream.asReader().reset();
			}
		}
		catch ( IOException ioe ) {
			throw new SQLException( "could not reset reader", ioe );
		}
		needsReset = true;
	}
