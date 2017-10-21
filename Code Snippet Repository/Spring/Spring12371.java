	@Override
	public void setDynamicAttribute(String uri, String localName, Object value ) throws JspException {
		if (this.dynamicAttributes == null) {
			this.dynamicAttributes = new HashMap<>();
		}
		if (!isValidDynamicAttribute(localName, value)) {
			throw new IllegalArgumentException(
					"Attribute " + localName + "=\"" + value + "\" is not allowed");
		}
		dynamicAttributes.put(localName, value);
	}
