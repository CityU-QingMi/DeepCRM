	@Override
	public void setBlobAsBinaryStream(
			PreparedStatement ps, int paramIndex, @Nullable InputStream binaryStream, int contentLength)
			throws SQLException {

		if (binaryStream != null) {
			Blob blob = ps.getConnection().createBlob();
			try {
				FileCopyUtils.copy(binaryStream, blob.setBinaryStream(1));
			}
			catch (IOException ex) {
				throw new DataAccessResourceFailureException("Could not copy into LOB stream", ex);
			}
			this.temporaryBlobs.add(blob);
			ps.setBlob(paramIndex, blob);
		}
		else {
			ps.setBlob(paramIndex, (Blob) null);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(binaryStream != null ?
					"Copied binary stream into temporary BLOB with length " + contentLength :
					"Set BLOB to null");
		}
	}
