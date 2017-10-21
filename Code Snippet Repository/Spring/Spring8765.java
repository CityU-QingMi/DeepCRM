	@Test
	public void testContentNegotiation() throws Exception {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Person.class);

		List<View> viewList = new ArrayList<>();
		viewList.add(new MappingJackson2JsonView());
		viewList.add(new MarshallingView(marshaller));

		ContentNegotiationManager manager = new ContentNegotiationManager(
				new HeaderContentNegotiationStrategy(), new FixedContentNegotiationStrategy(MediaType.TEXT_HTML));

		ContentNegotiatingViewResolver cnViewResolver = new ContentNegotiatingViewResolver();
		cnViewResolver.setDefaultViews(viewList);
		cnViewResolver.setContentNegotiationManager(manager);
		cnViewResolver.afterPropertiesSet();

		MockMvc mockMvc =
			standaloneSetup(new PersonController())
				.setViewResolvers(cnViewResolver, new InternalResourceViewResolver())
				.build();

		mockMvc.perform(get("/person/Corea"))
			.andExpect(status().isOk())
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("person"))
			.andExpect(forwardedUrl("person/show"));

		mockMvc.perform(get("/person/Corea").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.person.name").value("Corea"));

		mockMvc.perform(get("/person/Corea").accept(MediaType.APPLICATION_XML))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_XML))
			.andExpect(xpath("/person/name/text()").string(equalTo("Corea")));
	}
