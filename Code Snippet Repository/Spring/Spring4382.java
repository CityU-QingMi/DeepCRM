	private static void validateContainerType(Class<? extends Annotation> annotationType,
			Class<? extends Annotation> containerType) {

		try {
			Method method = containerType.getDeclaredMethod(AnnotationUtils.VALUE);
			Class<?> returnType = method.getReturnType();
			if (!returnType.isArray() || returnType.getComponentType() != annotationType) {
				String msg = String.format(
						"Container type [%s] must declare a 'value' attribute for an array of type [%s]",
						containerType.getName(), annotationType.getName());
				throw new AnnotationConfigurationException(msg);
			}
		}
		catch (Throwable ex) {
			AnnotationUtils.rethrowAnnotationConfigurationException(ex);
			String msg = String.format("Invalid declaration of container type [%s] for repeatable annotation [%s]",
					containerType.getName(), annotationType.getName());
			throw new AnnotationConfigurationException(msg, ex);
		}
	}
