	public EvaluationContext createEvaluationContext(ApplicationEvent event, Class<?> targetClass,
			Method method, Object[] args, @Nullable BeanFactory beanFactory) {

		Method targetMethod = getTargetMethod(targetClass, method);
		EventExpressionRootObject root = new EventExpressionRootObject(event, args);
		MethodBasedEvaluationContext evaluationContext = new MethodBasedEvaluationContext(
				root, targetMethod, args, getParameterNameDiscoverer());
		if (beanFactory != null) {
			evaluationContext.setBeanResolver(new BeanFactoryResolver(beanFactory));
		}
		return evaluationContext;
	}
