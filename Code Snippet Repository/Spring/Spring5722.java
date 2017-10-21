	@Override
	public String toStringAST() {
		StringBuilder sb = new StringBuilder("T(");
		sb.append(getChild(0).toStringAST());
		for (int d = 0; d < this.dimensions; d++) {
			sb.append("[]");
		}
		sb.append(")");
		return sb.toString();
	}
