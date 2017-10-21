	@Test
	public void testAbstractLobStreamingResultSetExtractorMultipleRows()
			throws SQLException {
		ResultSet rset = mock(ResultSet.class);
		given(rset.next()).willReturn(true, true, false);
		AbstractLobStreamingResultSetExtractor<Void> lobRse = getResultSetExtractor(false);
		thrown.expect(IncorrectResultSizeDataAccessException.class);
		try {
			lobRse.extractData(rset);
		}
		finally {
			verify(rset).clearWarnings();
		}
	}
