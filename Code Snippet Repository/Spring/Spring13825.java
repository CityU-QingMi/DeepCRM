	public static ManagedList<? super Object> parseBeanSubElements(@Nullable Element parentElement, ParserContext context) {
		ManagedList<? super Object> beans = new ManagedList<>();
		if (parentElement != null) {
			beans.setSource(context.extractSource(parentElement));
			for (Element beanElement : DomUtils.getChildElementsByTagName(parentElement, "bean", "ref")) {
				beans.add(context.getDelegate().parsePropertySubElement(beanElement, null));
			}
		}
		return beans;
	}
