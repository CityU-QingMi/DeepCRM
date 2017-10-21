	@Override
	public String toStringAST() {
		StringBuilder sb = new StringBuilder();
		switch (this.variant) {
			case ALL:
				sb.append("?[");
				break;
			case FIRST:
				sb.append("^[");
				break;
			case LAST:
				sb.append("$[");
				break;
		}
		return sb.append(getChild(0).toStringAST()).append("]").toString();
	}
