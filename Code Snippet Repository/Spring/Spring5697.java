	@Override
	public BooleanTypedValue getValueInternal(ExpressionState state) throws EvaluationException {
		Object left = getLeftOperand().getValueInternal(state).getValue();
		Object right = getRightOperand().getValueInternal(state).getValue();
		if (!(right instanceof List) || ((List<?>) right).size() != 2) {
			throw new SpelEvaluationException(getRightOperand().getStartPosition(),
					SpelMessage.BETWEEN_RIGHT_OPERAND_MUST_BE_TWO_ELEMENT_LIST);
		}

		List<?> list = (List<?>) right;
		Object low = list.get(0);
		Object high = list.get(1);
		TypeComparator comp = state.getTypeComparator();
		try {
			return BooleanTypedValue.forValue(comp.compare(left, low) >= 0 && comp.compare(left, high) <= 0);
		}
		catch (SpelEvaluationException ex) {
			ex.setPosition(getStartPosition());
			throw ex;
		}
	}
