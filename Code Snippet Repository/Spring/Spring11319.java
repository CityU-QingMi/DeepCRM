	@Override
	public Mono<View> resolveViewName(String viewName, Locale locale) {
		if (!canHandle(viewName, locale)) {
			return Mono.empty();
		}

		AbstractUrlBasedView urlBasedView;
		if (viewName.startsWith(REDIRECT_URL_PREFIX)) {
			String redirectUrl = viewName.substring(REDIRECT_URL_PREFIX.length());
			urlBasedView = this.redirectViewProvider.apply(redirectUrl);
		}
		else {
			urlBasedView = createView(viewName);
		}

		View view = applyLifecycleMethods(viewName, urlBasedView);
		try {
			return (urlBasedView.checkResourceExists(locale) ? Mono.just(view) : Mono.empty());
		}
		catch (Exception ex) {
			return Mono.error(ex);
		}
	}
