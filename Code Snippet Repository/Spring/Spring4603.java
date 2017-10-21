	public NettyDataBuffer write(ByteBuf... byteBufs) {
		Assert.notNull(byteBufs, "'byteBufs' must not be null");

		CompositeByteBuf composite = new CompositeByteBuf(
				this.byteBuf.alloc(), this.byteBuf.isDirect(), byteBufs.length + 1);
		composite.addComponent(this.byteBuf);
		composite.addComponents(byteBufs);

		int writerIndex = this.byteBuf.readableBytes() +
				Arrays.stream(byteBufs).mapToInt(ByteBuf::readableBytes).sum();
		composite.writerIndex(writerIndex);

		this.byteBuf = composite;
		return this;
	}
