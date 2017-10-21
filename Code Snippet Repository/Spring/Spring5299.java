	@Test
	public void testGetReadableByteChannel() throws IOException {
		Resource resource = new FileSystemResource(getClass().getResource("Resource.class").getFile());
		ReadableByteChannel channel = null;
		try {
			channel = resource.readableChannel();
			ByteBuffer buffer = ByteBuffer.allocate((int) resource.contentLength());
			channel.read(buffer);
			buffer.rewind();
			assertTrue(buffer.limit() > 0);
		}
		finally {
			if (channel != null) {
				channel.close();
			}
		}
	}
