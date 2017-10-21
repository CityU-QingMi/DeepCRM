	@Override
	@Nullable
	public MessageCodesResolver getMessageCodesResolver() {
		MessageCodesResolver selected = null;
		for (WebMvcConfigurer configurer : this.delegates) {
			MessageCodesResolver messageCodesResolver = configurer.getMessageCodesResolver();
			if (messageCodesResolver != null) {
				if (selected != null) {
					throw new IllegalStateException("No unique MessageCodesResolver found: {" +
							selected + ", " + messageCodesResolver + "}");
				}
				selected = messageCodesResolver;
			}
		}
		return selected;
	}
