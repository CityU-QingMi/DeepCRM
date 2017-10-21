	@Test
	public void requestMappingArgumentResolvers() throws Exception {

		InvocableHandlerMethod invocable = this.methodResolver.getRequestMappingMethod(this.handlerMethod);
		List<HandlerMethodArgumentResolver> resolvers = invocable.getResolvers();

		AtomicInteger index = new AtomicInteger(-1);
		assertEquals(RequestParamMethodArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(RequestParamMapMethodArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(PathVariableMethodArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(PathVariableMapMethodArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(RequestBodyArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(RequestPartMethodArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(ModelAttributeMethodArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(RequestHeaderMethodArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(RequestHeaderMapMethodArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(CookieValueMethodArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(ExpressionValueMethodArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(SessionAttributeMethodArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(RequestAttributeMethodArgumentResolver.class, next(resolvers, index).getClass());

		assertEquals(HttpEntityArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(ModelArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(ErrorsMethodArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(ServerWebExchangeArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(PrincipalArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(SessionStatusMethodArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(WebSessionArgumentResolver.class, next(resolvers, index).getClass());

		assertEquals(CustomArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(CustomSyncArgumentResolver.class, next(resolvers, index).getClass());

		assertEquals(RequestParamMethodArgumentResolver.class, next(resolvers, index).getClass());
		assertEquals(ModelAttributeMethodArgumentResolver.class, next(resolvers, index).getClass());
	}
