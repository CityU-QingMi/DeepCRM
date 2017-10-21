	private boolean validateIfModifiedSince(long lastModifiedTimestamp) {
		if (lastModifiedTimestamp < 0) {
			return false;
		}
		long ifModifiedSince = parseDateHeader(IF_MODIFIED_SINCE);
		if (ifModifiedSince == -1) {
			return false;
		}
		// We will perform this validation...
		this.notModified = ifModifiedSince >= (lastModifiedTimestamp / 1000 * 1000);
		return true;
	}
