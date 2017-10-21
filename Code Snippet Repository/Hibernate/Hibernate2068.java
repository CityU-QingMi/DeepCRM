	private void resetIfNeeded() throws SQLException {
		try {
			if ( needsReset ) {
				binaryStream.getInputStream().reset();
			}
		}
		catch ( IOException ioe) {
			throw new SQLException("could not reset reader");
		}
		needsReset = true;
	}
