	@Override
	public boolean checkNotModified(@Nullable String etag, long lastModifiedTimestamp) {
		HttpServletResponse response = getResponse();
		if (this.notModified || (response != null && HttpStatus.OK.value() != response.getStatus())) {
			return this.notModified;
		}

		// Evaluate conditions in order of precedence.
		// See https://tools.ietf.org/html/rfc7232#section-6

		if (validateIfUnmodifiedSince(lastModifiedTimestamp)) {
			if (this.notModified && response != null) {
				response.setStatus(HttpStatus.PRECONDITION_FAILED.value());
			}
			return this.notModified;
		}

		boolean validated = validateIfNoneMatch(etag);
		if (!validated) {
			validateIfModifiedSince(lastModifiedTimestamp);
		}

		// Update response
		if (response != null) {
			boolean isHttpGetOrHead = SAFE_METHODS.contains(getRequest().getMethod());
			if (this.notModified) {
				response.setStatus(isHttpGetOrHead ?
						HttpStatus.NOT_MODIFIED.value() : HttpStatus.PRECONDITION_FAILED.value());
			}
			if (isHttpGetOrHead) {
				if (lastModifiedTimestamp > 0 && parseDateValue(response.getHeader(LAST_MODIFIED)) == -1) {
					response.setDateHeader(LAST_MODIFIED, lastModifiedTimestamp);
				}
				if (StringUtils.hasLength(etag) && response.getHeader(ETAG) == null) {
					response.setHeader(ETAG, padEtagIfNecessary(etag));
				}
			}
		}

		return this.notModified;
	}
