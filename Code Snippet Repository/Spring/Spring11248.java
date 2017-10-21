	protected <A extends Annotation> boolean checkAnnotatedParamNoReactiveWrapper(
			MethodParameter parameter, Class<A> annotationType, BiPredicate<A, Class<?>> typePredicate) {

		A annotation = parameter.getParameterAnnotation(annotationType);
		if (annotation == null) {
			return false;
		}

		parameter = parameter.nestedIfOptional();
		Class<?> type = parameter.getNestedParameterType();

		ReactiveAdapter adapter = getAdapterRegistry().getAdapter(type);
		if (adapter != null) {
			assertHasValues(adapter, parameter);
			parameter = parameter.nested();
			type = parameter.getNestedParameterType();
		}

		if (typePredicate.test(annotation, type)) {
			if (adapter == null) {
				return true;
			}
			throw buildReactiveWrapperException(parameter);
		}

		return false;
	}
