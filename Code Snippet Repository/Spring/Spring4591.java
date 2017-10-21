	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DefaultDataBuffer)) {
			return false;
		}
		DefaultDataBuffer other = (DefaultDataBuffer) obj;
		return (this.readPosition == other.readPosition &&
				this.writePosition == other.writePosition &&
				this.byteBuffer.equals(other.byteBuffer));
	}
