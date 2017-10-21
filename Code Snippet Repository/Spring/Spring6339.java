		@Override
		public void setClobAsAsciiStream(
				PreparedStatement ps, int paramIndex, @Nullable InputStream asciiStream, int contentLength)
				throws SQLException {

			if (streamAsLob) {
				if (asciiStream != null) {
					Reader reader = new InputStreamReader(asciiStream, StandardCharsets.US_ASCII);
					if (contentLength >= 0) {
						ps.setClob(paramIndex, reader, contentLength);
					}
					else {
						ps.setClob(paramIndex, reader);
					}
				}
				else {
					ps.setClob(paramIndex, (Clob) null);
				}
			}
			else if (wrapAsLob) {
				if (asciiStream != null) {
					ps.setClob(paramIndex, new PassThroughClob(asciiStream, contentLength));
				}
				else {
					ps.setClob(paramIndex, (Clob) null);
				}
			}
			else if (contentLength >= 0) {
				ps.setAsciiStream(paramIndex, asciiStream, contentLength);
			}
			else {
				ps.setAsciiStream(paramIndex, asciiStream);
			}
			if (logger.isDebugEnabled()) {
				logger.debug(asciiStream != null ? "Set ASCII stream for CLOB with length " + contentLength :
						"Set CLOB to null");
			}
		}
