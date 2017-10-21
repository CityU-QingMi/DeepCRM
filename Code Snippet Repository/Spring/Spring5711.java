	@Override
	public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
		// Cache the concatenation of child identifiers
		if (this.value == null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < getChildCount(); i++) {
				Object value = this.children[i].getValueInternal(state).getValue();
				if (i > 0 && (value == null || !value.toString().startsWith("$"))) {
					sb.append(".");
				}
				sb.append(value);
			}
			this.value = new TypedValue(sb.toString());
		}
		return this.value;
	}
