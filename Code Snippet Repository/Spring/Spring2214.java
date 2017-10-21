	public ApplicationListenerMethodAdapter(String beanName, Class<?> targetClass, Method method) {
		this.beanName = beanName;
		this.method = method;
		this.targetClass = targetClass;
		this.bridgedMethod = BridgeMethodResolver.findBridgedMethod(method);

		Method targetMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
		EventListener ann = AnnotatedElementUtils.findMergedAnnotation(targetMethod, EventListener.class);

		this.declaredEventTypes = resolveDeclaredEventTypes(method, ann);
		this.condition = (ann != null ? ann.condition() : null);
		this.order = resolveOrder(method);

		this.methodKey = new AnnotatedElementKey(method, targetClass);
	}
