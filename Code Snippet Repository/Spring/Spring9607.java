	private boolean validateIfNoneMatch(@Nullable String etag) {
		if (!StringUtils.hasLength(etag)) {
			return false;
		}

		Enumeration<String> ifNoneMatch;
		try {
			ifNoneMatch = getRequest().getHeaders(IF_NONE_MATCH);
		}
		catch (IllegalArgumentException ex) {
			return false;
		}
		if (!ifNoneMatch.hasMoreElements()) {
			return false;
		}

		// We will perform this validation...
		etag = padEtagIfNecessary(etag);
		while (ifNoneMatch.hasMoreElements()) {
			String clientETags = ifNoneMatch.nextElement();
			Matcher etagMatcher = ETAG_HEADER_VALUE_PATTERN.matcher(clientETags);
			// Compare weak/strong ETags as per https://tools.ietf.org/html/rfc7232#section-2.3
			while (etagMatcher.find()) {
				if (StringUtils.hasLength(etagMatcher.group()) &&
						etag.replaceFirst("^W/", "").equals(etagMatcher.group(3))) {
					this.notModified = true;
					break;
				}
			}
		}

		return true;
	}
