	public Object getAttribute(String name, int scope) {

		if (name == null) {
			throw new NullPointerException(Localizer
					.getMessage("jsp.error.attribute.null_name"));
		}

		if (scope == PAGE_SCOPE) {
			return pageAttributes.get(name);
		}

		return invokingJspCtxt.getAttribute(name, scope);
	}
