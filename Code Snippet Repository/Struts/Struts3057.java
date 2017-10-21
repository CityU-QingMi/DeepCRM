	private void doRemoveAttribute(String name, int scope) {
		switch (scope) {
		case PAGE_SCOPE:
			attributes.remove(name);
			break;

		case REQUEST_SCOPE:
			request.removeAttribute(name);
			break;

		case SESSION_SCOPE:
			if (session == null) {
				throw new IllegalStateException(Localizer
						.getMessage("jsp.error.page.noSession"));
			}
			session.removeAttribute(name);
			break;

		case APPLICATION_SCOPE:
			context.removeAttribute(name);
			break;

		default:
			throw new IllegalArgumentException("Invalid scope");
		}
	}
