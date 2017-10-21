	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof NettyDataBuffer)) {
			return false;
		}
		NettyDataBuffer other = (NettyDataBuffer) obj;
		return this.byteBuf.equals(other.byteBuf);
	}
