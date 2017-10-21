	@Test
	public void resolverArgument() throws Exception {

		BindingContext context = new BindingContext();
		WebSession session = mock(WebSession.class);
		WebSessionManager manager = exchange -> Mono.just(session);
		MockServerHttpRequest request = MockServerHttpRequest.get("/").build();
		ServerWebExchange exchange = new DefaultServerWebExchange(request, new MockServerHttpResponse(),
				manager, ServerCodecConfigurer.create(), new AcceptHeaderLocaleContextResolver());

		MethodParameter param = this.testMethod.arg(WebSession.class);
		Object actual = this.resolver.resolveArgument(param, context, exchange).block();
		assertSame(session, actual);

		param = this.testMethod.arg(Mono.class, WebSession.class);
		actual = this.resolver.resolveArgument(param, context, exchange).block();
		assertNotNull(actual);
		assertTrue(Mono.class.isAssignableFrom(actual.getClass()));
		assertSame(session, ((Mono<?>) actual).block());

		param = this.testMethod.arg(Single.class, WebSession.class);
		actual = this.resolver.resolveArgument(param, context, exchange).block();
		assertNotNull(actual);
		assertTrue(Single.class.isAssignableFrom(actual.getClass()));
		assertSame(session, ((Single<?>) actual).blockingGet());
	}
