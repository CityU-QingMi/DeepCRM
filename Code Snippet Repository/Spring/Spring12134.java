	@Nullable
	protected Object adaptArgumentIfNecessary(@Nullable Object arg, MethodParameter parameter) {
		if (parameter.getParameterType() == Optional.class) {
			if (arg == null || (arg instanceof Collection && ((Collection) arg).isEmpty()) ||
					(arg instanceof Object[] && ((Object[]) arg).length == 0)) {
				return Optional.empty();
			}
			else {
				return Optional.of(arg);
			}
		}
		return arg;
	}
