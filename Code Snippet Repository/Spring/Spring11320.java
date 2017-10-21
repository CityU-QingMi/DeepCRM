	protected AbstractUrlBasedView createView(String viewName) {
		Class<?> viewClass = getViewClass();
		Assert.state(viewClass != null, "No view class");

		AbstractUrlBasedView view = (AbstractUrlBasedView) BeanUtils.instantiateClass(viewClass);
		view.setSupportedMediaTypes(getSupportedMediaTypes());
		view.setRequestContextAttribute(getRequestContextAttribute());
		view.setDefaultCharset(getDefaultCharset());
		view.setUrl(getPrefix() + viewName + getSuffix());

		return view;
	}
