	private void printCookies(Cookie[] cookies) {
		String[] cookieStrings = new String[cookies.length];
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			cookieStrings[i] = new ToStringCreator(cookie)
				.append("name", cookie.getName())
				.append("value", cookie.getValue())
				.append("comment", cookie.getComment())
				.append("domain", cookie.getDomain())
				.append("maxAge", cookie.getMaxAge())
				.append("path", cookie.getPath())
				.append("secure", cookie.getSecure())
				.append("version", cookie.getVersion())
				.append("httpOnly", cookie.isHttpOnly())
				.toString();
		}
		this.printer.printValue("Cookies", cookieStrings);
	}
