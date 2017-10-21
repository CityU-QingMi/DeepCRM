	@Override
	public void setClobAsAsciiStream(
			PreparedStatement ps, int paramIndex, @Nullable InputStream asciiStream, int contentLength)
			throws SQLException {

		if (asciiStream != null) {
			Clob clob = ps.getConnection().createClob();
			try {
				FileCopyUtils.copy(asciiStream, clob.setAsciiStream(1));
			}
			catch (IOException ex) {
				throw new DataAccessResourceFailureException("Could not copy into LOB stream", ex);
			}
			this.temporaryClobs.add(clob);
			ps.setClob(paramIndex, clob);
		}
		else {
			ps.setClob(paramIndex, (Clob) null);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(asciiStream != null ?
					"Copied ASCII stream into temporary CLOB with length " + contentLength :
					"Set CLOB to null");
		}
	}
