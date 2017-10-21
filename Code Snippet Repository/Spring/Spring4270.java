	public static String getVariableNameForParameter(MethodParameter parameter) {
		Assert.notNull(parameter, "MethodParameter must not be null");
		Class<?> valueClass;
		boolean pluralize = false;
		String reactiveSuffix = "";

		if (parameter.getParameterType().isArray()) {
			valueClass = parameter.getParameterType().getComponentType();
			pluralize = true;
		}
		else if (Collection.class.isAssignableFrom(parameter.getParameterType())) {
			valueClass = ResolvableType.forMethodParameter(parameter).asCollection().resolveGeneric();
			if (valueClass == null) {
				throw new IllegalArgumentException(
						"Cannot generate variable name for non-typed Collection parameter type");
			}
			pluralize = true;
		}
		else {
			valueClass = parameter.getParameterType();

			if (reactiveAdapterRegistry.hasAdapters()) {
				ReactiveAdapter adapter = reactiveAdapterRegistry.getAdapter(valueClass);
				if (adapter != null && !adapter.getDescriptor().isNoValue()) {
					reactiveSuffix = ClassUtils.getShortName(valueClass);
					valueClass = parameter.nested().getNestedParameterType();
				}
			}
		}

		String name = ClassUtils.getShortNameAsProperty(valueClass);
		return (pluralize ? pluralize(name) : name + reactiveSuffix);
	}
