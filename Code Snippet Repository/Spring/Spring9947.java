	public String toDetailedString() {
		StringBuilder buf = new StringBuilder();
		buf.append(this.pattern).append('\n');
		for (int i = 0; i < this.position; i++) {
			buf.append(' ');
		}
		buf.append("^\n");
		buf.append(getMessage());
		return buf.toString();
	}
