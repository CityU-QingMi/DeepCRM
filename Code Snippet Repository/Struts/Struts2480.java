	public List<InterceptorMapping> build(Class<?> actionClass, PackageConfig.Builder builder,
			String actionName, Action annotation) {
		List<InterceptorMapping> interceptorList = new ArrayList<>(
				10);

		//from @InterceptorRefs annotation
        InterceptorRefs interceptorRefs = AnnotationUtils.findAnnotation(actionClass, InterceptorRefs.class);
        if (interceptorRefs != null)
            interceptorList.addAll(build(interceptorRefs.value(), actionName, builder));

        //from @InterceptorRef annotation
        InterceptorRef interceptorRef = AnnotationUtils.findAnnotation(actionClass, InterceptorRef.class);
        if (interceptorRef != null)
            interceptorList.addAll(build(new InterceptorRef[] {interceptorRef}, actionName, builder));

		//from @Action annotation
		if (annotation != null) {
			InterceptorRef[] interceptors = annotation.interceptorRefs();
			if (interceptors != null) {
			    interceptorList.addAll(build(interceptors, actionName, builder));
			}
		}

		return interceptorList;
	}
