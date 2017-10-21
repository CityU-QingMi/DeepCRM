	@Override
	public String toStringAST() {
		StringBuilder sb = new StringBuilder("{");
		int count = getChildCount();
		for (int c = 0; c < count; c++) {
			if (c > 0) {
				sb.append(",");
			}
			sb.append(getChild(c++).toStringAST());
			sb.append(":");
			sb.append(getChild(c).toStringAST());
		}
		sb.append("}");
		return sb.toString();
	}
