	private void doTestMultipartHttpServletRequest(MultipartHttpServletRequest request) throws IOException {
		Set<String> fileNames = new HashSet<>();
		Iterator<String> fileIter = request.getFileNames();
		while (fileIter.hasNext()) {
			fileNames.add(fileIter.next());
		}
		assertEquals(2, fileNames.size());
		assertTrue(fileNames.contains("file1"));
		assertTrue(fileNames.contains("file2"));
		MultipartFile file1 = request.getFile("file1");
		MultipartFile file2 = request.getFile("file2");
		Map<String, MultipartFile> fileMap = request.getFileMap();
		List<String> fileMapKeys = new LinkedList<>(fileMap.keySet());
		assertEquals(2, fileMapKeys.size());
		assertEquals(file1, fileMap.get("file1"));
		assertEquals(file2, fileMap.get("file2"));

		assertEquals("file1", file1.getName());
		assertEquals("", file1.getOriginalFilename());
		assertNull(file1.getContentType());
		assertTrue(ObjectUtils.nullSafeEquals("myContent1".getBytes(), file1.getBytes()));
		assertTrue(ObjectUtils.nullSafeEquals("myContent1".getBytes(),
			FileCopyUtils.copyToByteArray(file1.getInputStream())));
		assertEquals("file2", file2.getName());
		assertEquals("myOrigFilename", file2.getOriginalFilename());
		assertEquals("text/plain", file2.getContentType());
		assertTrue(ObjectUtils.nullSafeEquals("myContent2".getBytes(), file2.getBytes()));
		assertTrue(ObjectUtils.nullSafeEquals("myContent2".getBytes(),
			FileCopyUtils.copyToByteArray(file2.getInputStream())));
	}
