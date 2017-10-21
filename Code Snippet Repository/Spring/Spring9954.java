	@Test
	public void encodeHeaderFieldParam() {
		Method encode = ReflectionUtils.findMethod(ContentDisposition.class,
				"encodeHeaderFieldParam", String.class, Charset.class);
		ReflectionUtils.makeAccessible(encode);

		String result = (String)ReflectionUtils.invokeMethod(encode, null, "test.txt",
				StandardCharsets.US_ASCII);
		assertEquals("test.txt", result);

		result = (String)ReflectionUtils.invokeMethod(encode, null, "中文.txt", StandardCharsets.UTF_8);
		assertEquals("UTF-8''%E4%B8%AD%E6%96%87.txt", result);
	}
