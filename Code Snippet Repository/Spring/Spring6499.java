	private AbstractLobStreamingResultSetExtractor<Void> getResultSetExtractor(final boolean ex) {
		AbstractLobStreamingResultSetExtractor<Void> lobRse = new AbstractLobStreamingResultSetExtractor<Void>() {

			@Override
			protected void streamData(ResultSet rs) throws SQLException, IOException {
				if (ex) {
					throw new IOException();
				}
				else {
					rs.clearWarnings();
				}
			}
		};
		return lobRse;
	}
