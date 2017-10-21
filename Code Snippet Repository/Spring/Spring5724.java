	@Override
	public ValueRef getValueRef(ExpressionState state) throws SpelEvaluationException {
		if (this.name.equals(THIS)) {
			return new ValueRef.TypedValueHolderValueRef(state.getActiveContextObject(),this);
		}
		if (this.name.equals(ROOT)) {
			return new ValueRef.TypedValueHolderValueRef(state.getRootContextObject(),this);
		}
		TypedValue result = state.lookupVariable(this.name);
		// a null value will mean either the value was null or the variable was not found
		return new VariableRef(this.name,result,state.getEvaluationContext());
	}
