	@Nullable
	public static Annotation[] getAnnotations(Method method) {
		try {
			return synthesizeAnnotationArray(BridgeMethodResolver.findBridgedMethod(method).getAnnotations(), method);
		}
		catch (Throwable ex) {
			handleIntrospectionFailure(method, ex);
			return null;
		}
	}
