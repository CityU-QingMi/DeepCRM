	public void setAttribute(String name, Object value, int scope) {

		if (name == null) {
			throw new NullPointerException(Localizer
					.getMessage("jsp.error.attribute.null_name"));
		}

		if (scope == PAGE_SCOPE) {
			if (value != null) {
				pageAttributes.put(name, value);
			} else {
				removeAttribute(name, PAGE_SCOPE);
			}
		} else {
			invokingJspCtxt.setAttribute(name, value, scope);
		}
	}
