	@Test
	public void renderNoModelKeyAndBindingResultFirst() throws Exception {
		Object toBeMarshalled = new Object();
		String modelKey = "key";
		Map<String, Object> model = new LinkedHashMap<>();
		model.put(BindingResult.MODEL_KEY_PREFIX + modelKey, new BeanPropertyBindingResult(toBeMarshalled, modelKey));
		model.put(modelKey, toBeMarshalled);

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		given(marshallerMock.supports(BeanPropertyBindingResult.class)).willReturn(true);
		given(marshallerMock.supports(Object.class)).willReturn(true);

		view.render(model, request, response);
		assertEquals("Invalid content type", "application/xml", response.getContentType());
		assertEquals("Invalid content length", 0, response.getContentLength());
		verify(marshallerMock).marshal(eq(toBeMarshalled), isA(StreamResult.class));
	}
