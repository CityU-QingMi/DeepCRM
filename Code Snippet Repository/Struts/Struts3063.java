	private Enumeration doGetAttributeNamesInScope(int scope) {
		switch (scope) {
		case PAGE_SCOPE:
			return new Enumerator(attributes.keySet().iterator());

		case REQUEST_SCOPE:
			return request.getAttributeNames();

		case SESSION_SCOPE:
			if (session == null) {
				throw new IllegalStateException(Localizer
						.getMessage("jsp.error.page.noSession"));
			}
			return session.getAttributeNames();

		case APPLICATION_SCOPE:
			return context.getAttributeNames();

		default:
			throw new IllegalArgumentException("Invalid scope");
		}
	}
