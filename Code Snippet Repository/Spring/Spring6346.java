	@Override
	public void setClobAsCharacterStream(
			PreparedStatement ps, int paramIndex, @Nullable Reader characterStream, int contentLength)
			throws SQLException {

		if (characterStream != null) {
			Clob clob = ps.getConnection().createClob();
			try {
				FileCopyUtils.copy(characterStream, clob.setCharacterStream(1));
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
			logger.debug(characterStream != null ?
					"Copied character stream into temporary CLOB with length " + contentLength :
					"Set CLOB to null");
		}
	}
