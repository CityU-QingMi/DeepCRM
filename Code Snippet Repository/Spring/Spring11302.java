	@Override
	public boolean supports(HandlerResult result) {
		Class<?> valueType = resolveReturnValueType(result);
		if (isSupportedType(valueType)) {
			return true;
		}
		ReactiveAdapter adapter = getAdapter(result);
		return adapter != null && !adapter.isNoValue() &&
				isSupportedType(result.getReturnType().getGeneric().resolve(Object.class));
	}
