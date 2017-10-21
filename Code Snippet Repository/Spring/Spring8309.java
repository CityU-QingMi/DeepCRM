	private void cookies(MockHttpServletRequest request) {
		List<Cookie> cookies = new ArrayList<>();

		String cookieHeaderValue = header("Cookie");
		if (cookieHeaderValue != null) {
			StringTokenizer tokens = new StringTokenizer(cookieHeaderValue, "=;");
			while (tokens.hasMoreTokens()) {
				String cookieName = tokens.nextToken().trim();
				Assert.isTrue(tokens.hasMoreTokens(),
						() -> "Expected value for cookie name '" + cookieName +
								"': full cookie header was [" + cookieHeaderValue + "]");
				String cookieValue = tokens.nextToken().trim();
				processCookie(request, cookies, new Cookie(cookieName, cookieValue));
			}
		}

		Set<com.gargoylesoftware.htmlunit.util.Cookie> managedCookies = this.webClient.getCookies(this.webRequest.getUrl());
		for (com.gargoylesoftware.htmlunit.util.Cookie cookie : managedCookies) {
			processCookie(request, cookies, new Cookie(cookie.getName(), cookie.getValue()));
		}

		Cookie[] parentCookies = request.getCookies();
		if (parentCookies != null) {
			for (Cookie cookie : parentCookies) {
				cookies.add(cookie);
			}
		}

		if (!ObjectUtils.isEmpty(cookies)) {
			request.setCookies(cookies.toArray(new Cookie[cookies.size()]));
		}
	}
