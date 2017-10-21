	public void insertTestData(TestData testData) throws SQLException {
		Connection cn = null;
		try {
			cn = getDataSource().getConnection();
			cn.setAutoCommit( false );
			Statement stmt = cn.createStatement();
			for ( TestDataElement testDataElement : testData ) {
				String sql = sqlExpressionTemplate.toInsertSql( testDataElement );
				LOG.debug( "adding stmt: " + sql );
				stmt.addBatch( sql );
			}
			int[] insCounts = stmt.executeBatch();
			cn.commit();
			stmt.close();
			LOG.info( "Loaded " + sum( insCounts ) + " rows." );
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			try {
				if ( cn != null ) {
					cn.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
