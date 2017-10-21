	protected Map<String, Object> getHints(ResolvableType resolvableType) {
		MethodParameter param = getParameter(resolvableType);
		if (param != null) {
			JsonView annotation = getAnnotation(param, JsonView.class);
			if (annotation != null) {
				Class<?>[] classes = annotation.value();
				Assert.isTrue(classes.length == 1, JSON_VIEW_HINT_ERROR + param);
				return Collections.singletonMap(JSON_VIEW_HINT, classes[0]);
			}
		}
		return Collections.emptyMap();
	}
