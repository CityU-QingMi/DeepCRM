	protected PathExtensionContentNegotiationStrategy initContentNegotiationStrategy() {
		Map<String, MediaType> mediaTypes = null;
		if (getContentNegotiationManager() != null) {
			PathExtensionContentNegotiationStrategy strategy =
					getContentNegotiationManager().getStrategy(PathExtensionContentNegotiationStrategy.class);
			if (strategy != null) {
				mediaTypes = new HashMap<>(strategy.getMediaTypes());
			}
		}
		return (getServletContext() != null ?
				new ServletPathExtensionContentNegotiationStrategy(getServletContext(), mediaTypes) :
				new PathExtensionContentNegotiationStrategy(mediaTypes));
	}
