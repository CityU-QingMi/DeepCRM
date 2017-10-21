	@Override
	public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
		Boolean value = this.children[0].getValue(state, Boolean.class);
		if (value == null) {
			throw new SpelEvaluationException(getChild(0).getStartPosition(),
					SpelMessage.TYPE_CONVERSION_ERROR, "null", "boolean");
		}
		TypedValue result = this.children[value ? 1 : 2].getValueInternal(state);
		computeExitTypeDescriptor();
		return result;
	}
