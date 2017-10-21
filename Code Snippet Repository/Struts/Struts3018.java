	public int getAttributesScope(String name) {

		if (name == null) {
			throw new NullPointerException(Localizer
					.getMessage("jsp.error.attribute.null_name"));
		}

		if (pageAttributes.get(name) != null) {
			return PAGE_SCOPE;
		} else {
			return invokingJspCtxt.getAttributesScope(name);
		}
	}
