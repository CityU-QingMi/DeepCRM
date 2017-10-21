	@Test
	public void resolveMultipartFileArray() throws Exception {
		Object actual = resolver.resolveArgument(paramMultipartFileArray, null, webRequest, null);
		assertNotNull(actual);
		assertTrue(actual instanceof MultipartFile[]);
		MultipartFile[] parts = (MultipartFile[]) actual;
		assertEquals(2, parts.length);
		assertEquals(parts[0], multipartFile1);
		assertEquals(parts[1], multipartFile2);
	}
