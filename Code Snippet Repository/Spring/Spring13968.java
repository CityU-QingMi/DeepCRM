	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SockJsFrame)) {
			return false;
		}
		SockJsFrame otherFrame = (SockJsFrame) other;
		return (this.type.equals(otherFrame.type) && this.content.equals(otherFrame.content));
	}
