	@Override
	public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
		if (!this.nonAnnotatedClasses.contains(bean.getClass())) {
			Class<?> targetClass = AopUtils.getTargetClass(bean);
			Map<Method, Set<JmsListener>> annotatedMethods = MethodIntrospector.selectMethods(targetClass,
					(MethodIntrospector.MetadataLookup<Set<JmsListener>>) method -> {
						Set<JmsListener> listenerMethods = AnnotatedElementUtils.getMergedRepeatableAnnotations(
								method, JmsListener.class, JmsListeners.class);
						return (!listenerMethods.isEmpty() ? listenerMethods : null);
					});
			if (annotatedMethods.isEmpty()) {
				this.nonAnnotatedClasses.add(bean.getClass());
				if (logger.isTraceEnabled()) {
					logger.trace("No @JmsListener annotations found on bean type: " + bean.getClass());
				}
			}
			else {
				// Non-empty set of methods
				annotatedMethods.forEach((method, listeners) ->
						listeners.forEach(listener ->
								processJmsListener(listener, method, bean)));
				if (logger.isDebugEnabled()) {
					logger.debug(annotatedMethods.size() + " @JmsListener methods processed on bean '" + beanName +
							"': " + annotatedMethods);
				}
			}
		}
		return bean;
	}
