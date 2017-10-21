	@Override
	public String toStringAST() {
		StringBuilder sb = new StringBuilder("new ");
		int index = 0;
		sb.append(getChild(index++).toStringAST());
		sb.append("(");
		for (int i = index; i < getChildCount(); i++) {
			if (i > index) {
				sb.append(",");
			}
			sb.append(getChild(i).toStringAST());
		}
		sb.append(")");
		return sb.toString();
	}
