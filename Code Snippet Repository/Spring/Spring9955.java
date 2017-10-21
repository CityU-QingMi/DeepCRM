	@Test
	public void decodeHeaderFieldParam() {
		Method decode = ReflectionUtils.findMethod(ContentDisposition.class,
				"decodeHeaderFieldParam", String.class);
		ReflectionUtils.makeAccessible(decode);

		String result = (String)ReflectionUtils.invokeMethod(decode, null, "test.txt");
		assertEquals("test.txt", result);

		result = (String)ReflectionUtils.invokeMethod(decode, null, "UTF-8''%E4%B8%AD%E6%96%87.txt");
		assertEquals("中文.txt", result);
	}
