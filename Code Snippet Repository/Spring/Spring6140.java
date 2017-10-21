	@Nullable
	private static String getSeparator(Element element, Element scriptElement) {
		String scriptSeparator = scriptElement.getAttribute("separator");
		if (StringUtils.hasLength(scriptSeparator)) {
			return scriptSeparator;
		}
		String elementSeparator = element.getAttribute("separator");
		if (StringUtils.hasLength(elementSeparator)) {
			return elementSeparator;
		}
		return null;
	}
