	@Test
	public void explicitStrategies() throws Exception {
		Map<String, MediaType> mediaTypes = Collections.singletonMap("bar", new MediaType("application", "bar"));
		ParameterContentNegotiationStrategy strategy1 = new ParameterContentNegotiationStrategy(mediaTypes);
		HeaderContentNegotiationStrategy strategy2 = new HeaderContentNegotiationStrategy();
		List<ContentNegotiationStrategy> strategies = Arrays.asList(strategy1, strategy2);
		this.factoryBean.setStrategies(strategies);
		this.factoryBean.afterPropertiesSet();
		ContentNegotiationManager manager = this.factoryBean.getObject();

		assertEquals(strategies, manager.getStrategies());

		this.servletRequest.setRequestURI("/flower");
		this.servletRequest.addParameter("format", "bar");
		assertEquals(Collections.singletonList(new MediaType("application", "bar")),
				manager.resolveMediaTypes(this.webRequest));

	}
