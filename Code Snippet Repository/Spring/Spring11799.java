	@Test
	public void resolveOptional() throws Exception {
		MethodParameter param = initMethodParameter(3);
		Mono<Object> mono = this.resolver.resolveArgument(param, new BindingContext(), this.exchange);

		assertNotNull(mono.block());
		assertEquals(Optional.class, mono.block().getClass());
		assertFalse(((Optional<?>) mono.block()).isPresent());

		ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
		initializer.setConversionService(new DefaultFormattingConversionService());
		BindingContext bindingContext = new BindingContext(initializer);

		Foo foo = new Foo();
		this.exchange.getAttributes().put("foo", foo);
		mono = this.resolver.resolveArgument(param, bindingContext, this.exchange);

		assertNotNull(mono.block());
		assertEquals(Optional.class, mono.block().getClass());
		Optional<?> optional = (Optional<?>) mono.block();
		assertTrue(optional.isPresent());
		assertSame(foo, optional.get());
	}
