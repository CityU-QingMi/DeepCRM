	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append('=').append(getValue());
		if (StringUtils.hasText(getPath())) {
			sb.append("; Path=").append(getPath());
		}
		if (StringUtils.hasText(this.domain)) {
			sb.append("; Domain=").append(this.domain);
		}
		if (!this.maxAge.isNegative()) {
			sb.append("; Max-Age=").append(this.maxAge);
			sb.append("; Expires=");
			HttpHeaders headers = new HttpHeaders();
			long seconds = this.maxAge.getSeconds();
			headers.setExpires(seconds > 0 ? System.currentTimeMillis() + seconds : 0);
			sb.append(headers.getFirst(HttpHeaders.EXPIRES));
		}

		if (this.secure) {
			sb.append("; Secure");
		}
		if (this.httpOnly) {
			sb.append("; HttpOnly");
		}
		return sb.toString();
	}
