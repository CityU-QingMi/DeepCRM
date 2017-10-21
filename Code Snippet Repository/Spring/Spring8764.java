	@Test
	public void testXmlOnly() throws Exception {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Person.class);

		standaloneSetup(new PersonController()).setSingleView(new MarshallingView(marshaller)).build()
			.perform(get("/person/Corea"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_XML))
				.andExpect(xpath("/person/name/text()").string(equalTo("Corea")));
	}
