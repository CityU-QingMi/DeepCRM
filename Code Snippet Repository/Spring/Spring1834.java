	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("CacheInvocationContext{");
		sb.append("operation=").append(this.operation);
		sb.append(", target=").append(this.target);
		sb.append(", args=").append(Arrays.toString(this.args));
		sb.append(", allParameters=").append(Arrays.toString(this.allParameters));
		sb.append('}');
		return sb.toString();
	}
