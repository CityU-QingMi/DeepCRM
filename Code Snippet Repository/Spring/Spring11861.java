	@Test
	public void resolve() throws Exception {
		MethodParameter param = initMethodParameter(0);
		Mono<Object> mono = this.resolver.resolveArgument(param, new BindingContext(), this.exchange);
		StepVerifier.create(mono).expectError(ServerWebInputException.class).verify();

		Foo foo = new Foo();
		when(this.session.getAttribute("foo")).thenReturn(foo);
		mono = this.resolver.resolveArgument(param, new BindingContext(), this.exchange);
		assertSame(foo, mono.block());
	}
