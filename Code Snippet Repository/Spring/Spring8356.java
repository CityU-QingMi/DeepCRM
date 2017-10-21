	@Override
	public RequestPostProcessor beforeMockMvcCreated(ConfigurableMockMvcBuilder<?> builder,
			WebApplicationContext context) {

		return request -> {
			if (this.session != null) {
				request.setSession(this.session);
			}
			return request;
		};
	}
