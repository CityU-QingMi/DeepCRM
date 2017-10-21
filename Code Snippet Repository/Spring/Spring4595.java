	@Override
	public DataBuffer write(DataBuffer... buffers) {
		if (!ObjectUtils.isEmpty(buffers)) {
			ByteBuffer[] byteBuffers =
					Arrays.stream(buffers).map(DataBuffer::asByteBuffer)
							.toArray(ByteBuffer[]::new);
			write(byteBuffers);
		}
		return this;
	}
