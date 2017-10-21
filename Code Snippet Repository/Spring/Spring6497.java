	@Test
	public void testAbstractLobStreamingResultSetExtractorNoRows() throws SQLException {
		ResultSet rset = mock(ResultSet.class);
		AbstractLobStreamingResultSetExtractor<Void> lobRse = getResultSetExtractor(false);
		thrown.expect(IncorrectResultSizeDataAccessException.class);
		try {
			lobRse.extractData(rset);
		}
		finally {
			verify(rset).next();
		}
	}
