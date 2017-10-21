	@Override
	public boolean supports(HandlerResult result) {
		if (hasModelAnnotation(result.getReturnTypeSource())) {
			return true;
		}

		Class<?> type = result.getReturnType().getRawClass();
		ReactiveAdapter adapter = getAdapter(result);
		if (adapter != null) {
			if (adapter.isNoValue()) {
				return true;
			}
			type = result.getReturnType().getGeneric().resolve(Object.class);
		}

		return (type != null &&
				(CharSequence.class.isAssignableFrom(type) || Rendering.class.isAssignableFrom(type) ||
						Model.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type) ||
						void.class.equals(type) || View.class.isAssignableFrom(type) ||
						!BeanUtils.isSimpleProperty(type)));
	}
