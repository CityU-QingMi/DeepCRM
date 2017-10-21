	@Test
	@SuppressWarnings("")
	public void readXmlRootElementExternalEntityEnabled() throws Exception {
		Resource external = new ClassPathResource("external.txt", getClass());
		String content =  "<!DOCTYPE root [" +
				"  <!ELEMENT external ANY >\n" +
				"  <!ENTITY ext SYSTEM \"" + external.getURI() + "\" >]>" +
				"  <list><rootElement><type s=\"1\"/><external>&ext;</external></rootElement></list>";
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(content.getBytes("UTF-8"));

		Jaxb2CollectionHttpMessageConverter<?> c = new Jaxb2CollectionHttpMessageConverter<Collection<Object>>() {
			@Override
			protected XMLInputFactory createXmlInputFactory() {
				XMLInputFactory inputFactory = XMLInputFactory.newInstance();
				inputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, true);
				return inputFactory;
			}
		};

		Collection<RootElement> result = c.read(rootElementListType, null, inputMessage);
		assertEquals(1, result.size());
		assertEquals("Foo Bar", result.iterator().next().external);
	}
