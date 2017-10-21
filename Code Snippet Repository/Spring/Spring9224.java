	private ResponseCookie(String name, String value, Duration maxAge, @Nullable String domain,
			@Nullable String path, boolean secure, boolean httpOnly) {

		super(name, value);
		Assert.notNull(maxAge, "Max age must not be null");
		this.maxAge = maxAge;
		this.domain = domain;
		this.path = path;
		this.secure = secure;
		this.httpOnly = httpOnly;
	}
