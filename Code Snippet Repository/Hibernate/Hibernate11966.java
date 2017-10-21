	public void prepareTest() {
		try {
			dataSourceUtils.insertTestData( testData );
		}
		catch (BatchUpdateException e) {
			throw new RuntimeException( e.getNextException() );
		}
		catch (SQLException e) {
			throw new RuntimeException( e );
		}
	}
