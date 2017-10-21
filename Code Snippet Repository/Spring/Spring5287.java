	@Test
	public void getReadableByteChannelForDir() throws Exception {
		PathResource resource = new PathResource(TEST_DIR);
		try {
			resource.readableChannel();
		}
		catch (AccessDeniedException ex) {
			// on Windows
		}
	}
