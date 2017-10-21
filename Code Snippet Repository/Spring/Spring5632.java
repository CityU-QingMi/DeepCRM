	@Override
	public String toStringAST() {
		StringBuilder sb = new StringBuilder();
		if (!this.beanName.startsWith(FACTORY_BEAN_PREFIX)) {
			sb.append("@");
		}
		if (!this.beanName.contains(".")) {
			sb.append(this.beanName);
		}
		else {
			sb.append("'").append(this.beanName).append("'");
		}
		return sb.toString();
	}
