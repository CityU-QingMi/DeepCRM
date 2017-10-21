	@Test
	public void renderModelKeyWithJaxbElement() throws Exception {
		String toBeMarshalled = "value";
		String modelKey = "key";
		view.setModelKey(modelKey);
		Map<String, Object> model = new HashMap<>();
		model.put(modelKey, new JAXBElement<>(new QName("model"), String.class, toBeMarshalled));

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		given(marshallerMock.supports(String.class)).willReturn(true);
		marshallerMock.marshal(eq(toBeMarshalled), isA(StreamResult.class));

		view.render(model, request, response);
		assertEquals("Invalid content type", "application/xml", response.getContentType());
		assertEquals("Invalid content length", 0, response.getContentLength());
	}
