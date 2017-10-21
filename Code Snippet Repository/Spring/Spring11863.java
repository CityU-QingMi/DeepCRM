	@SuppressWarnings("")
	@Test
	public void resolveOptional() throws Exception {
		MethodParameter param = initMethodParameter(3);
		Optional<Object> actual = (Optional<Object>) this.resolver
				.resolveArgument(param, new BindingContext(), this.exchange).block();

		assertNotNull(actual);
		assertFalse(actual.isPresent());

		ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
		initializer.setConversionService(new DefaultFormattingConversionService());
		BindingContext bindingContext = new BindingContext(initializer);

		Foo foo = new Foo();
		when(this.session.getAttribute("foo")).thenReturn(foo);
		actual = (Optional<Object>) this.resolver.resolveArgument(param, bindingContext, this.exchange).block();

		assertNotNull(actual);
		assertTrue(actual.isPresent());
		assertSame(foo, actual.get());
	}
