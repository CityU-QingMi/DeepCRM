	@Override
	protected MultiValueMap<String, HttpCookie> initCookies() {
		MultiValueMap<String, HttpCookie> cookies = new LinkedMultiValueMap<>();
		for (String name : this.request.getCookies().keySet()) {
			for (Cookie cookie : this.request.getCookies().get(name)) {
				HttpCookie httpCookie = new HttpCookie(name, cookie.value());
				cookies.add(name, httpCookie);
			}
		}
		return cookies;
	}
