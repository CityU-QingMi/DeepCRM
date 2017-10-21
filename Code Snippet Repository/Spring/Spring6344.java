	@Override
	public void setClobAsString(PreparedStatement ps, int paramIndex, @Nullable String content)
			throws SQLException {

		if (content != null) {
			Clob clob = ps.getConnection().createClob();
			clob.setString(1, content);
			this.temporaryClobs.add(clob);
			ps.setClob(paramIndex, clob);
		}
		else {
			ps.setClob(paramIndex, (Clob) null);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(content != null ? "Copied string into temporary CLOB with length " + content.length() :
					"Set CLOB to null");
		}
	}
