	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WebSocketHttpHeaders)) {
			return false;
		}
		WebSocketHttpHeaders otherHeaders = (WebSocketHttpHeaders) other;
		return this.headers.equals(otherHeaders.headers);
	}
