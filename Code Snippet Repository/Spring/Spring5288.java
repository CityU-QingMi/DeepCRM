	@Test
	public void getWritableChannel() throws Exception {
		PathResource resource = new PathResource(temporaryFolder.newFile("test").toPath());
		ByteBuffer buffer = ByteBuffer.wrap("test".getBytes(StandardCharsets.UTF_8));
		WritableByteChannel channel = null;
		try {
			channel = resource.writableChannel();
			channel.write(buffer);
		}
		finally {
			if (channel != null) {
				channel.close();
			}
		}
		assertThat(resource.contentLength(), equalTo(4L));
	}
