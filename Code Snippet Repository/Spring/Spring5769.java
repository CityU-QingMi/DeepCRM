	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[").append(this.kind.toString());
		if (this.kind.hasPayload()) {
			s.append(":").append(this.data);
		}
		s.append("]");
		s.append("(").append(this.startPos).append(",").append(this.endPos).append(")");
		return s.toString();
	}
