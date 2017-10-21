	private Object doGetAttribute(String name, int scope) {
		switch (scope) {
		case PAGE_SCOPE:
			return attributes.get(name);

		case REQUEST_SCOPE:
			return request.getAttribute(name);

		case SESSION_SCOPE:
			if (session == null) {
				throw new IllegalStateException(Localizer
						.getMessage("jsp.error.page.noSession"));
			}
			return session.getAttribute(name);

		case APPLICATION_SCOPE:
			return context.getAttribute(name);

		default:
			throw new IllegalArgumentException("Invalid scope");
		}
	}
