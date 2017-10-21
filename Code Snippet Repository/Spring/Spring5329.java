	@Test
	public void dumpString() {
		DataBuffer buffer = this.bufferFactory.allocateBuffer(4);
		String source = "abcd";
		buffer.write(source.getBytes(StandardCharsets.UTF_8));

		String result = DataBufferTestUtils.dumpString(buffer, StandardCharsets.UTF_8);

		assertEquals(source, result);

		release(buffer);
	}
