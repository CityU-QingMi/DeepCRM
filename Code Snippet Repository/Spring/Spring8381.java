	@Test
	public void mockMultipartHttpServletRequestWithByteArray() throws IOException {
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		assertFalse(request.getFileNames().hasNext());
		assertNull(request.getFile("file1"));
		assertNull(request.getFile("file2"));
		assertTrue(request.getFileMap().isEmpty());

		request.addFile(new MockMultipartFile("file1", "myContent1".getBytes()));
		request.addFile(new MockMultipartFile("file2", "myOrigFilename", "text/plain", "myContent2".getBytes()));
		doTestMultipartHttpServletRequest(request);
	}
