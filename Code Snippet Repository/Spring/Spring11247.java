	protected boolean checkParameterTypeNoReactiveWrapper(MethodParameter parameter, Predicate<Class<?>> predicate) {
		Class<?> type = parameter.getParameterType();
		ReactiveAdapter adapter = getAdapterRegistry().getAdapter(type);
		if (adapter != null) {
			assertHasValues(adapter, parameter);
			type = parameter.nested().getNestedParameterType();
		}
		if (predicate.test(type)) {
			if (adapter == null) {
				return true;
			}
			throw buildReactiveWrapperException(parameter);
		}
		return false;
	}
