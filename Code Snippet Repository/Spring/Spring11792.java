	@Test
	public void resolveArgumentNotRequired() throws Exception {
		Map<String, String> uriTemplateVars = new HashMap<>();
		uriTemplateVars.put("name", "value");
		this.exchange.getAttributes().put(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, uriTemplateVars);

		BindingContext bindingContext = new BindingContext();
		Mono<Object> mono = this.resolver.resolveArgument(this.paramNotRequired, bindingContext, this.exchange);
		Object result = mono.block();
		assertEquals("value", result);
	}
