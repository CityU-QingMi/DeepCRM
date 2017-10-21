	private List<ResolvableType> resolveDeclaredEventTypes(Method method, @Nullable EventListener ann) {
		int count = method.getParameterCount();
		if (count > 1) {
			throw new IllegalStateException(
					"Maximum one parameter is allowed for event listener method: " + method);
		}
		if (ann != null && ann.classes().length > 0) {
			List<ResolvableType> types = new ArrayList<>(ann.classes().length);
			for (Class<?> eventType : ann.classes()) {
				types.add(ResolvableType.forClass(eventType));
			}
			return types;
		}
		else {
			if (count == 0) {
				throw new IllegalStateException(
						"Event parameter is mandatory for event listener method: " + method);
			}
			return Collections.singletonList(ResolvableType.forMethodParameter(method, 0));
		}
	}
