	@Override
	public TypedValue getValueInternal(ExpressionState state) throws SpelEvaluationException {
		if (this.name.equals(THIS)) {
			return state.getActiveContextObject();
		}
		if (this.name.equals(ROOT)) {
			TypedValue result = state.getRootContextObject();
			this.exitTypeDescriptor = CodeFlow.toDescriptorFromObject(result.getValue());
			return result;
		}
		TypedValue result = state.lookupVariable(this.name);
		Object value = result.getValue();
		if (value == null || !Modifier.isPublic(value.getClass().getModifiers())) {
			// If the type is not public then when generateCode produces a checkcast to it
			// then an IllegalAccessError will occur.
			// If resorting to Object isn't sufficient, the hierarchy could be traversed for 
			// the first public type.
			this.exitTypeDescriptor = "Ljava/lang/Object";
		}
		else {
			this.exitTypeDescriptor = CodeFlow.toDescriptorFromObject(value);
		}
		// a null value will mean either the value was null or the variable was not found
		return result;
	}
