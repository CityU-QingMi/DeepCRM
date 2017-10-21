	@Test
	@SuppressWarnings("")
	public void readXmlRootElementExternalEntityDisabled() throws Exception {
		Resource external = new ClassPathResource("external.txt", getClass());
		String content =  "<!DOCTYPE root [" +
				"  <!ELEMENT external ANY >\n" +
				"  <!ENTITY ext SYSTEM \"" + external.getURI() + "\" >]>" +
				"  <list><rootElement><type s=\"1\"/><external>&ext;</external></rootElement></list>";
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(content.getBytes("UTF-8"));

		converter = new Jaxb2CollectionHttpMessageConverter<Collection<Object>>() {
			@Override
			protected XMLInputFactory createXmlInputFactory() {
				XMLInputFactory inputFactory = super.createXmlInputFactory();
				inputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, true);
				return inputFactory;
			}
		};

		try {
			Collection<RootElement> result = converter.read(rootElementListType, null, inputMessage);
			assertEquals(1, result.size());
			assertEquals("", result.iterator().next().external);
		}
		catch (HttpMessageNotReadableException ex) {
			// Some parsers raise an exception
		}
	}
