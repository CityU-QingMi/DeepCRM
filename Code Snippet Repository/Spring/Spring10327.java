	private String getCookieHeader(Cookie cookie) {
		StringBuilder buf = new StringBuilder();
		buf.append(cookie.getName()).append('=').append(cookie.getValue() == null ? "" : cookie.getValue());
		if (StringUtils.hasText(cookie.getPath())) {
			buf.append("; Path=").append(cookie.getPath());
		}
		if (StringUtils.hasText(cookie.getDomain())) {
			buf.append("; Domain=").append(cookie.getDomain());
		}
		int maxAge = cookie.getMaxAge();
		if (maxAge >= 0) {
			buf.append("; Max-Age=").append(maxAge);
			buf.append("; Expires=");
			HttpHeaders headers = new HttpHeaders();
			headers.setExpires(maxAge > 0 ? System.currentTimeMillis() + 1000L * maxAge : 0);
			buf.append(headers.getFirst(HttpHeaders.EXPIRES));
		}

		if (cookie.getSecure()) {
			buf.append("; Secure");
		}
		if (cookie.isHttpOnly()) {
			buf.append("; HttpOnly");
		}
		return buf.toString();
	}
