	@Test
	public void read() throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setContentType(MediaType.TEXT_PLAIN_VALUE);

		Short shortValue = Short.valueOf((short) 781);
		request.setContent(shortValue.toString().getBytes(
				StringHttpMessageConverter.DEFAULT_CHARSET));
		assertEquals(shortValue, this.converter.read(Short.class, new ServletServerHttpRequest(request)));

		Float floatValue = Float.valueOf(123);
		request.setCharacterEncoding("UTF-16");
		request.setContent(floatValue.toString().getBytes("UTF-16"));
		assertEquals(floatValue, this.converter.read(Float.class, new ServletServerHttpRequest(request)));

		Long longValue = Long.valueOf(55819182821331L);
		request.setCharacterEncoding("UTF-8");
		request.setContent(longValue.toString().getBytes("UTF-8"));
		assertEquals(longValue, this.converter.read(Long.class, new ServletServerHttpRequest(request)));
	}
