	public TextMessage build() {
		StringBuilder sb = new StringBuilder(this.command.name()).append("\n");
		for (String line : this.headerLines) {
			sb.append(line).append("\n");
		}
		sb.append("\n");
		if (this.body != null) {
			sb.append(this.body);
		}
		sb.append("\u0000");
		return new TextMessage(sb.toString());
	}
