	@Test
	public void renderNoModelKey() throws Exception {
		Object toBeMarshalled = new Object();
		String modelKey = "key";
		Map<String, Object> model = new HashMap<>();
		model.put(modelKey, toBeMarshalled);

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		given(marshallerMock.supports(Object.class)).willReturn(true);

		view.render(model, request, response);
		assertEquals("Invalid content type", "application/xml", response.getContentType());
		assertEquals("Invalid content length", 0, response.getContentLength());
		verify(marshallerMock).marshal(eq(toBeMarshalled), isA(StreamResult.class));
	}
