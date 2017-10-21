	@Override
	@Nullable
	protected SimpMessageMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
		MessageMapping messageAnn = AnnotatedElementUtils.findMergedAnnotation(method, MessageMapping.class);
		if (messageAnn != null) {
			MessageMapping typeAnn = AnnotatedElementUtils.findMergedAnnotation(handlerType, MessageMapping.class);
			// Only actually register it if there are destinations specified;
			// otherwise @MessageMapping is just being used as a (meta-annotation) marker.
			if (messageAnn.value().length > 0 || (typeAnn != null && typeAnn.value().length > 0)) {
				SimpMessageMappingInfo result = createMessageMappingCondition(messageAnn.value());
				if (typeAnn != null) {
					result = createMessageMappingCondition(typeAnn.value()).combine(result);
				}
				return result;
			}
		}

		SubscribeMapping subscribeAnn = AnnotatedElementUtils.findMergedAnnotation(method, SubscribeMapping.class);
		if (subscribeAnn != null) {
			MessageMapping typeAnn = AnnotatedElementUtils.findMergedAnnotation(handlerType, MessageMapping.class);
			// Only actually register it if there are destinations specified;
			// otherwise @SubscribeMapping is just being used as a (meta-annotation) marker.
			if (subscribeAnn.value().length > 0 || (typeAnn != null && typeAnn.value().length > 0)) {
				SimpMessageMappingInfo result = createSubscribeMappingCondition(subscribeAnn.value());
				if (typeAnn != null) {
					result = createMessageMappingCondition(typeAnn.value()).combine(result);
				}
				return result;
			}
		}

		return null;
	}
