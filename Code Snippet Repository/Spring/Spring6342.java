	@Override
	public void setBlobAsBytes(PreparedStatement ps, int paramIndex, @Nullable byte[] content)
			throws SQLException {

		if (content != null) {
			Blob blob = ps.getConnection().createBlob();
			blob.setBytes(1, content);
			this.temporaryBlobs.add(blob);
			ps.setBlob(paramIndex, blob);
		}
		else {
			ps.setBlob(paramIndex, (Blob) null);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(content != null ? "Copied bytes into temporary BLOB with length " + content.length :
					"Set BLOB to null");
		}
	}
