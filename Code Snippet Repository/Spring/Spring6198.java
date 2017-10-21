	@Override
	public boolean isGetGeneratedKeysSimulated() {
		String version = getDatabaseVersion();
		if (version != null && version.compareTo("8.2.0") >= 0) {
			return true;
		}
		else {
			if (logger.isWarnEnabled()) {
				logger.warn("PostgreSQL does not support getGeneratedKeys or INSERT ... RETURNING in version " +
						version);
			}
			return false;
		}
	}
