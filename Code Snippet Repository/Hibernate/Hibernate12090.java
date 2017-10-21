	private void validateCallbackMethod(Method method, CallbackType type, List<Throwable> errors) {
		if ( method.getParameterCount() > 0 ) {
			errors.add(
					new InvalidMethodForAnnotationException(
							type.buildTypeMarker() + " callback only valid on no-arg methods : "
									+ Helper.extractMethodName( method )
					)
			);
		}
		try {
			method.setAccessible( true );
		}
		catch (Exception e) {
			errors.add(
					new InvalidMethodForAnnotationException(
							type.buildTypeMarker() + " attached to inaccessible method and unable to make accessible"
					)
			);
		}
	}
