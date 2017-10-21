	@Override
	public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
		TypedValue tv = getValueInternal(state.getActiveContextObject(), state.getEvaluationContext(),
				state.getConfiguration().isAutoGrowNullReferences());
		PropertyAccessor accessorToUse = this.cachedReadAccessor;
		if (accessorToUse instanceof CompilablePropertyAccessor) {
			CompilablePropertyAccessor accessor = (CompilablePropertyAccessor) accessorToUse;
			this.exitTypeDescriptor = CodeFlow.toDescriptor(accessor.getPropertyType());
		}
		return tv;
	}
