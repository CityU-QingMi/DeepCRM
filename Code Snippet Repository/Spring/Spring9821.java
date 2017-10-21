	private boolean validateIfNoneMatch(@Nullable String etag) {
		if (!StringUtils.hasLength(etag)) {
			return false;
		}
		List<String> ifNoneMatch;
		try {
			ifNoneMatch = getRequestHeaders().getIfNoneMatch();
		}
		catch (IllegalArgumentException ex) {
			return false;
		}
		if (ifNoneMatch.isEmpty()) {
			return false;
		}
		// We will perform this validation...
		etag = padEtagIfNecessary(etag);
		for (String clientETag : ifNoneMatch) {
			// Compare weak/strong ETags as per https://tools.ietf.org/html/rfc7232#section-2.3
			if (StringUtils.hasLength(clientETag) &&
					clientETag.replaceFirst("^W/", "").equals(etag.replaceFirst("^W/", ""))) {
				this.notModified = true;
				break;
			}
		}
		return true;
	}
