	public char[] getChars() {
		StringBuilder b = new StringBuilder();
		b.append("{");
		b.append(this.variableName);
		if (this.constraintPattern != null) {
			b.append(":").append(this.constraintPattern.pattern());
		}
		b.append("}");
		return b.toString().toCharArray();
	}
