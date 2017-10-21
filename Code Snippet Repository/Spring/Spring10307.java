	private void updateContentTypeHeader() {
		if (StringUtils.hasLength(this.contentType)) {
			StringBuilder sb = new StringBuilder(this.contentType);
			if (!this.contentType.toLowerCase().contains(CHARSET_PREFIX) &&
					StringUtils.hasLength(this.characterEncoding)) {
				sb.append(";").append(CHARSET_PREFIX).append(this.characterEncoding);
			}
			doAddHeaderValue(HttpHeaders.CONTENT_TYPE, sb.toString(), true);
		}
	}
