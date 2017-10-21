	private boolean validateIfUnmodifiedSince(long lastModifiedTimestamp) {
		if (lastModifiedTimestamp < 0) {
			return false;
		}
		long ifUnmodifiedSince = parseDateHeader(IF_UNMODIFIED_SINCE);
		if (ifUnmodifiedSince == -1) {
			return false;
		}
		// We will perform this validation...
		this.notModified = (ifUnmodifiedSince < (lastModifiedTimestamp / 1000 * 1000));
		return true;
	}
