	public void setAttribute(String name, Object value) {

		if (name == null) {
			throw new NullPointerException(Localizer
					.getMessage("jsp.error.attribute.null_name"));
		}

		if (value != null) {
			pageAttributes.put(name, value);
		} else {
			removeAttribute(name, PAGE_SCOPE);
		}
	}
