	@Test
	public void getReadableByteChannel() throws Exception {
		PathResource resource = new PathResource(TEST_FILE);
		ReadableByteChannel channel = null;
		try {
			channel = resource.readableChannel();
			ByteBuffer buffer = ByteBuffer.allocate((int) resource.contentLength());
			channel.read(buffer);
			buffer.rewind();
			assertThat(buffer.limit(), greaterThan(0));
		}
		finally {
			if (channel != null) {
				channel.close();
			}
		}
	}
