	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("TestContainer{");
		sb.append("endpoint=").append(this.endpoint);
		sb.append(", startInvoked=").append(this.startInvoked);
		sb.append(", initializationInvoked=").append(this.initializationInvoked);
		sb.append(", stopInvoked=").append(this.stopInvoked);
		sb.append(", destroyInvoked=").append(this.destroyInvoked);
		sb.append('}');
		return sb.toString();
	}
