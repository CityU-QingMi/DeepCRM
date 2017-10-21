	private void doSetAttribute(String name, Object o, int scope) {
		if (o != null) {
			switch (scope) {
			case PAGE_SCOPE:
				attributes.put(name, o);
				break;

			case REQUEST_SCOPE:
				request.setAttribute(name, o);
				break;

			case SESSION_SCOPE:
				if (session == null) {
					throw new IllegalStateException(Localizer
							.getMessage("jsp.error.page.noSession"));
				}
				session.setAttribute(name, o);
				break;

			case APPLICATION_SCOPE:
				context.setAttribute(name, o);
				break;

			default:
				throw new IllegalArgumentException("Invalid scope");
			}
		} else {
			removeAttribute(name, scope);
		}
	}
