	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.httpMethod);
		sb.append(" ").append(this.uri);
		if (!getHeaders().isEmpty()) {
			sb.append(", headers: ").append(getHeaders());
		}
		if (sb.length() == 0) {
			sb.append("Not yet initialized");
		}
		return sb.toString();
	}
