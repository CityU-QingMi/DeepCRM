	@Override
	public NettyDataBuffer write(DataBuffer... buffers) {
		if (!ObjectUtils.isEmpty(buffers)) {
			if (buffers[0] instanceof NettyDataBuffer) {
				ByteBuf[] nativeBuffers = Arrays.stream(buffers)
						.map(b -> ((NettyDataBuffer) b).getNativeBuffer())
						.toArray(ByteBuf[]::new);
				write(nativeBuffers);
			}
			else {
				ByteBuffer[] byteBuffers =
						Arrays.stream(buffers).map(DataBuffer::asByteBuffer)
								.toArray(ByteBuffer[]::new);
				write(byteBuffers);
			}
		}
		return this;
	}
