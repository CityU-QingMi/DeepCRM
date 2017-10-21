	@Override
	public void close() {
		try {
			for (Blob blob : this.temporaryBlobs) {
				blob.free();
			}
			for (Clob clob : this.temporaryClobs) {
				clob.free();
			}
		}
		catch (SQLException ex) {
			logger.error("Could not free LOB", ex);
		}
	}
