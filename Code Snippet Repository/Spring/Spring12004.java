	private ContentNegotiatingViewResolver initContentNegotiatingViewResolver(View[] defaultViews) {
		// ContentNegotiatingResolver in the registry: elevate its precedence!
		this.order = (this.order != null ? this.order : Ordered.HIGHEST_PRECEDENCE);

		if (this.contentNegotiatingResolver != null) {
			if (!ObjectUtils.isEmpty(defaultViews)) {
				if (!CollectionUtils.isEmpty(this.contentNegotiatingResolver.getDefaultViews())) {
					List<View> views = new ArrayList<>(this.contentNegotiatingResolver.getDefaultViews());
					views.addAll(Arrays.asList(defaultViews));
					this.contentNegotiatingResolver.setDefaultViews(views);
				}
			}
		}
		else {
			this.contentNegotiatingResolver = new ContentNegotiatingViewResolver();
			this.contentNegotiatingResolver.setDefaultViews(Arrays.asList(defaultViews));
			this.contentNegotiatingResolver.setViewResolvers(this.viewResolvers);
			if (this.contentNegotiationManager != null) {
				this.contentNegotiatingResolver.setContentNegotiationManager(this.contentNegotiationManager);
			}
		}
		return this.contentNegotiatingResolver;
	}
