	private void initControllerAdviceCaches(@Nullable ApplicationContext applicationContext) {
		if (applicationContext == null) {
			return;
		}
		if (logger.isInfoEnabled()) {
			logger.info("Looking for @ControllerAdvice: " + applicationContext);
		}

		List<ControllerAdviceBean> beans = ControllerAdviceBean.findAnnotatedBeans(applicationContext);
		AnnotationAwareOrderComparator.sort(beans);

		for (ControllerAdviceBean bean : beans) {
			Class<?> beanType = bean.getBeanType();
			if (beanType != null) {
				Set<Method> attrMethods = selectMethods(beanType, ATTRIBUTE_METHODS);
				if (!attrMethods.isEmpty()) {
					this.modelAttributeAdviceCache.put(bean, attrMethods);
					if (logger.isInfoEnabled()) {
						logger.info("Detected @ModelAttribute methods in " + bean);
					}
				}
				Set<Method> binderMethods = selectMethods(beanType, BINDER_METHODS);
				if (!binderMethods.isEmpty()) {
					this.initBinderAdviceCache.put(bean, binderMethods);
					if (logger.isInfoEnabled()) {
						logger.info("Detected @InitBinder methods in " + bean);
					}
				}
				ExceptionHandlerMethodResolver resolver = new ExceptionHandlerMethodResolver(beanType);
				if (resolver.hasExceptionMappings()) {
					this.exceptionHandlerAdviceCache.put(bean, resolver);
					if (logger.isInfoEnabled()) {
						logger.info("Detected @ExceptionHandler methods in " + bean);
					}
				}
			}
		}
	}
