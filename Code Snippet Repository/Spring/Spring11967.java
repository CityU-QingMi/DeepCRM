	@Nullable
	private RuntimeBeanReference getAsyncExecutor(Element element) {
		Element asyncElement = DomUtils.getChildElementByTagName(element, "async-support");
		if (asyncElement != null) {
			if (asyncElement.hasAttribute("task-executor")) {
				return new RuntimeBeanReference(asyncElement.getAttribute("task-executor"));
			}
		}
		return null;
	}
