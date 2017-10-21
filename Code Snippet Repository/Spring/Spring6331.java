		@Override
		public void setClobAsCharacterStream(
				PreparedStatement ps, int paramIndex, @Nullable Reader characterStream, int contentLength)
				throws SQLException {

			if (streamAsLob) {
				if (characterStream != null) {
					if (contentLength >= 0) {
						ps.setClob(paramIndex, characterStream, contentLength);
					}
					else {
						ps.setClob(paramIndex, characterStream);
					}
				}
				else {
					ps.setClob(paramIndex, (Clob) null);
				}
			}
			else if (wrapAsLob) {
				if (characterStream != null) {
					ps.setClob(paramIndex, new PassThroughClob(characterStream, contentLength));
				}
				else {
					ps.setClob(paramIndex, (Clob) null);
				}
			}
			else if (contentLength >= 0) {
				ps.setCharacterStream(paramIndex, characterStream, contentLength);
			}
			else {
				ps.setCharacterStream(paramIndex, characterStream);
			}
			if (logger.isDebugEnabled()) {
				logger.debug(characterStream != null ? "Set character stream for CLOB with length " + contentLength :
						"Set CLOB to null");
			}
		}
