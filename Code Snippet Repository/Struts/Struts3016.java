	public void removeAttribute(String name) {

		if (name == null) {
			throw new NullPointerException(Localizer
					.getMessage("jsp.error.attribute.null_name"));
		}

		pageAttributes.remove(name);
		invokingJspCtxt.removeAttribute(name, REQUEST_SCOPE);
		if (getSession() != null) {
			invokingJspCtxt.removeAttribute(name, SESSION_SCOPE);
		}
		invokingJspCtxt.removeAttribute(name, APPLICATION_SCOPE);
	}
