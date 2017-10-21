	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(this.name);
		for (Map.Entry<String, String> entry : this.parameters.entrySet()) {
			str.append(';');
			str.append(entry.getKey());
			str.append('=');
			str.append(entry.getValue());
		}
		return str.toString();
	}
