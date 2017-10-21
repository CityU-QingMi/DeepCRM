	@Override
	public BooleanTypedValue getValueInternal(ExpressionState state) throws EvaluationException {
		Object left = getLeftOperand().getValueInternal(state).getValue();
		Object right = getRightOperand().getValueInternal(state).getValue();

		this.leftActualDescriptor = CodeFlow.toDescriptorFromObject(left);
		this.rightActualDescriptor = CodeFlow.toDescriptorFromObject(right);
		
		if (left instanceof Number && right instanceof Number) {
			Number leftNumber = (Number) left;
			Number rightNumber = (Number) right;

			if (leftNumber instanceof BigDecimal || rightNumber instanceof BigDecimal) {
				BigDecimal leftBigDecimal = NumberUtils.convertNumberToTargetClass(leftNumber, BigDecimal.class);
				BigDecimal rightBigDecimal = NumberUtils.convertNumberToTargetClass(rightNumber, BigDecimal.class);
				return BooleanTypedValue.forValue(leftBigDecimal.compareTo(rightBigDecimal) >= 0);
			}
			else if (leftNumber instanceof Double || rightNumber instanceof Double) {
				return BooleanTypedValue.forValue(leftNumber.doubleValue() >= rightNumber.doubleValue());
			}
			else if (leftNumber instanceof Float || rightNumber instanceof Float) {
				return BooleanTypedValue.forValue(leftNumber.floatValue() >= rightNumber.floatValue());
			}
			else if (leftNumber instanceof BigInteger || rightNumber instanceof BigInteger) {
				BigInteger leftBigInteger = NumberUtils.convertNumberToTargetClass(leftNumber, BigInteger.class);
				BigInteger rightBigInteger = NumberUtils.convertNumberToTargetClass(rightNumber, BigInteger.class);
				return BooleanTypedValue.forValue(leftBigInteger.compareTo(rightBigInteger) >= 0);
			}
			else if (leftNumber instanceof Long || rightNumber instanceof Long) {
				return BooleanTypedValue.forValue(leftNumber.longValue() >= rightNumber.longValue());
			}
			else if (leftNumber instanceof Integer || rightNumber instanceof Integer) {
				return BooleanTypedValue.forValue(leftNumber.intValue() >= rightNumber.intValue());
			}
			else if (leftNumber instanceof Short || rightNumber instanceof Short) {
				return BooleanTypedValue.forValue(leftNumber.shortValue() >= rightNumber.shortValue());
			}
			else if (leftNumber instanceof Byte || rightNumber instanceof Byte) {
				return BooleanTypedValue.forValue(leftNumber.byteValue() >= rightNumber.byteValue());
			}
			else {
				// Unknown Number subtypes -> best guess is double comparison
				return BooleanTypedValue.forValue(leftNumber.doubleValue() >= rightNumber.doubleValue());
			}
		}

		return BooleanTypedValue.forValue(state.getTypeComparator().compare(left, right) >= 0);
	}
