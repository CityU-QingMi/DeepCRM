	@Override
	public boolean equals(@Nullable Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		WebSocketExtension otherExt = (WebSocketExtension) other;
		return (this.name.equals(otherExt.name) && this.parameters.equals(otherExt.parameters));
	}
