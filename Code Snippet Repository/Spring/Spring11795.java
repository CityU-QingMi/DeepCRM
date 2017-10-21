	@Test
	public void resolverArgument() throws Exception {

		BindingContext context = new BindingContext();
		Principal user = () -> "Joe";
		ServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").build())
				.mutate().principal(Mono.just(user)).build();

		MethodParameter param = this.testMethod.arg(Principal.class);
		Object actual = this.resolver.resolveArgument(param, context, exchange).block();
		assertSame(user, actual);

		param = this.testMethod.arg(Mono.class, Principal.class);
		actual = this.resolver.resolveArgument(param, context, exchange).block();
		assertTrue(Mono.class.isAssignableFrom(actual.getClass()));
		assertSame(user, ((Mono<?>) actual).block());

		param = this.testMethod.arg(Single.class, Principal.class);
		actual = this.resolver.resolveArgument(param, context, exchange).block();
		assertTrue(Single.class.isAssignableFrom(actual.getClass()));
		assertSame(user, ((Single<?>) actual).blockingGet());
	}
