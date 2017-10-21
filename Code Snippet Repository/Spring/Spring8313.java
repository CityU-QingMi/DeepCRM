	@Override
	public Object merge(@Nullable Object parent) {
		if (parent instanceof RequestBuilder) {
			if (parent instanceof MockHttpServletRequestBuilder) {
				MockHttpServletRequestBuilder copiedParent = MockMvcRequestBuilders.get("/");
				copiedParent.merge(parent);
				this.parentBuilder = copiedParent;
			}
			else {
				this.parentBuilder = (RequestBuilder) parent;
			}
			if (parent instanceof SmartRequestBuilder) {
				this.parentPostProcessor = (SmartRequestBuilder) parent;
			}
		}
		return this;
	}
