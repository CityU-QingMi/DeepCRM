	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getClass().getName());
		sb.append(": advice ");
		if (this.adviceBeanName != null) {
			sb.append("bean '").append(this.adviceBeanName).append("'");
		}
		else {
			sb.append(this.advice);
		}
		return sb.toString();
	}
