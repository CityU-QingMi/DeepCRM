	private void storeCookies(WebRequest webRequest, javax.servlet.http.Cookie[] cookies) {
		Date now = new Date();
		CookieManager cookieManager = this.webClient.getCookieManager();
		for (javax.servlet.http.Cookie cookie : cookies) {
			if (cookie.getDomain() == null) {
				cookie.setDomain(webRequest.getUrl().getHost());
			}
			Cookie toManage = createCookie(cookie);
			Date expires = toManage.getExpires();
			if (expires == null || expires.after(now)) {
				cookieManager.addCookie(toManage);
			}
			else {
				cookieManager.removeCookie(toManage);
			}
		}
	}
