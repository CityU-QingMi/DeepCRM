	@Override
	public boolean checkNotModified(@Nullable String etag, Instant lastModified) {
		HttpStatus status = getResponse().getStatusCode();
		if (this.notModified || (status != null && !HttpStatus.OK.equals(status))) {
			return this.notModified;
		}

		// Evaluate conditions in order of precedence.
		// See https://tools.ietf.org/html/rfc7232#section-6

		if (validateIfUnmodifiedSince(lastModified)) {
			if (this.notModified) {
				getResponse().setStatusCode(HttpStatus.PRECONDITION_FAILED);
			}
			return this.notModified;
		}

		boolean validated = validateIfNoneMatch(etag);
		if (!validated) {
			validateIfModifiedSince(lastModified);
		}

		// Update response

		boolean isHttpGetOrHead = SAFE_METHODS.contains(getRequest().getMethod());
		if (this.notModified) {
			getResponse().setStatusCode(isHttpGetOrHead ?
					HttpStatus.NOT_MODIFIED : HttpStatus.PRECONDITION_FAILED);
		}
		if (isHttpGetOrHead) {
			if (lastModified.isAfter(Instant.EPOCH) && getResponseHeaders().getLastModified() == -1) {
				getResponseHeaders().setLastModified(lastModified.toEpochMilli());
			}
			if (StringUtils.hasLength(etag) && getResponseHeaders().getETag() == null) {
				getResponseHeaders().setETag(padEtagIfNecessary(etag));
			}
		}

		return this.notModified;
	}
