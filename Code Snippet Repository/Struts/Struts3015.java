	public Object findAttribute(String name) {

		if (name == null) {
			throw new NullPointerException(Localizer
					.getMessage("jsp.error.attribute.null_name"));
		}

		Object o = pageAttributes.get(name);
		if (o == null) {
			o = invokingJspCtxt.getAttribute(name, REQUEST_SCOPE);
			if (o == null) {
				if (getSession() != null) {
					o = invokingJspCtxt.getAttribute(name, SESSION_SCOPE);
				}
				if (o == null) {
					o = invokingJspCtxt.getAttribute(name, APPLICATION_SCOPE);
				}
			}
		}

		return o;
	}
