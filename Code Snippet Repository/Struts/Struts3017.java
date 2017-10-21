	public void removeAttribute(String name, int scope) {

		if (name == null) {
			throw new NullPointerException(Localizer
					.getMessage("jsp.error.attribute.null_name"));
		}

		if (scope == PAGE_SCOPE) {
			pageAttributes.remove(name);
		} else {
			invokingJspCtxt.removeAttribute(name, scope);
		}
	}
