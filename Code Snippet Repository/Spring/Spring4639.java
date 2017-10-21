	@Override
	public String style(@Nullable Object value) {
		if (value == null) {
			return NULL;
		}
		else if (value instanceof String) {
			return "\'" + value + "\'";
		}
		else if (value instanceof Class) {
			return ClassUtils.getShortName((Class<?>) value);
		}
		else if (value instanceof Method) {
			Method method = (Method) value;
			return method.getName() + "@" + ClassUtils.getShortName(method.getDeclaringClass());
		}
		else if (value instanceof Map) {
			return style((Map<?, ?>) value);
		}
		else if (value instanceof Map.Entry) {
			return style((Map.Entry<? ,?>) value);
		}
		else if (value instanceof Collection) {
			return style((Collection<?>) value);
		}
		else if (value.getClass().isArray()) {
			return styleArray(ObjectUtils.toObjectArray(value));
		}
		else {
			return String.valueOf(value);
		}
	}
