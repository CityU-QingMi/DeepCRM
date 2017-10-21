	@Test
	public void supportsParameter() {

		List<HandlerMethodArgumentResolver> resolvers = new ArrayList<>();
		resolvers.add(new RequestParamMethodArgumentResolver(false));
		resolvers.add(new RequestHeaderMethodArgumentResolver(null));
		resolvers.add(new RequestParamMethodArgumentResolver(true));

		Method method = ClassUtils.getMethod(this.getClass(), "handleRequest", String.class, String.class, String.class);

		CompositeUriComponentsContributor contributor = new CompositeUriComponentsContributor(resolvers);
		assertTrue(contributor.supportsParameter(new MethodParameter(method, 0)));
		assertTrue(contributor.supportsParameter(new MethodParameter(method, 1)));
		assertFalse(contributor.supportsParameter(new MethodParameter(method, 2)));
	}
