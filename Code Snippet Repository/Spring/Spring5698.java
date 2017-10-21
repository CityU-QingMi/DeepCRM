	@Override
	public BooleanTypedValue getValueInternal(ExpressionState state) throws EvaluationException {
		SpelNodeImpl rightOperand = getRightOperand();
		TypedValue left = getLeftOperand().getValueInternal(state);
		TypedValue right = rightOperand.getValueInternal(state);
		Object leftValue = left.getValue();
		Object rightValue = right.getValue();
		BooleanTypedValue result;
		if (rightValue == null || !(rightValue instanceof Class)) {
			throw new SpelEvaluationException(getRightOperand().getStartPosition(),
					SpelMessage.INSTANCEOF_OPERATOR_NEEDS_CLASS_OPERAND,
					(rightValue == null ? "null" : rightValue.getClass().getName()));
		}
		Class<?> rightClass = (Class<?>) rightValue;
		if (leftValue == null) {
			result = BooleanTypedValue.FALSE;  // null is not an instanceof anything
		}
		else {
			result = BooleanTypedValue.forValue(rightClass.isAssignableFrom(leftValue.getClass()));
		}
		this.type = rightClass;
		if (rightOperand instanceof TypeReference) {
			// Can only generate bytecode where the right operand is a direct type reference, 
			// not if it is indirect (for example when right operand is a variable reference)
			this.exitTypeDescriptor = "Z";
		}
		return result;
	}
