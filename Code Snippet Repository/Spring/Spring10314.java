	@Override
	public StringBuffer getRequestURL() {
		StringBuffer url = new StringBuffer(this.scheme).append("://").append(this.serverName);
		if (this.serverPort > 0 && ((HTTP.equalsIgnoreCase(this.scheme) && this.serverPort != 80) ||
				(HTTPS.equalsIgnoreCase(this.scheme) && this.serverPort != 443))) {
			url.append(':').append(this.serverPort);
		}
		if (StringUtils.hasText(getRequestURI())) {
			url.append(getRequestURI());
		}
		return url;
	}
