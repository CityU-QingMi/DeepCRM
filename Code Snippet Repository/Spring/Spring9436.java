	@Override
	public void addCookie(ResponseCookie cookie) {
		Assert.notNull(cookie, "'cookie' must not be null");

		if (this.state.get() == State.COMMITTED) {
			throw new IllegalStateException("Can't add the cookie " + cookie +
					"because the HTTP response has already been committed");
		}
		else {
			getCookies().add(cookie.getName(), cookie);
		}
	}
