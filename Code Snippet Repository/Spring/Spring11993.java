	private RootBeanDefinition getRedirectView(Element element, @Nullable HttpStatus status, @Nullable Object source) {
		RootBeanDefinition redirectView = new RootBeanDefinition(RedirectView.class);
		redirectView.setSource(source);
		redirectView.getConstructorArgumentValues().addIndexedArgumentValue(0, element.getAttribute("redirect-url"));

		if (status != null) {
			redirectView.getPropertyValues().add("statusCode", status);
		}

		if (element.hasAttribute("context-relative")) {
			redirectView.getPropertyValues().add("contextRelative", element.getAttribute("context-relative"));
		}
		else {
			redirectView.getPropertyValues().add("contextRelative", true);
		}

		if (element.hasAttribute("keep-query-params")) {
			redirectView.getPropertyValues().add("propagateQueryParams", element.getAttribute("keep-query-params"));
		}

		return redirectView;
	}
