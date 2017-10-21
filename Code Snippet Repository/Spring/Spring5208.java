	@Test
	public void byteBufferToByteBuffer() throws Exception {
		byte[] bytes = new byte[] { 1, 2, 3 };
		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
		ByteBuffer convert = this.conversionService.convert(byteBuffer, ByteBuffer.class);
		assertThat(convert, not(sameInstance(byteBuffer.rewind())));
		assertThat(convert, equalTo(byteBuffer.rewind()));
		assertThat(convert, equalTo(ByteBuffer.wrap(bytes)));
		assertThat(convert.array(), equalTo(bytes));
	}
