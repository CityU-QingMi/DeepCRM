	private void initMessagingAdviceCache(@Nullable List<MessagingAdviceBean> beans) {
		if (beans == null) {
			return;
		}
		for (MessagingAdviceBean bean : beans) {
			Class<?> type = bean.getBeanType();
			if (type != null) {
				AnnotationExceptionHandlerMethodResolver resolver = new AnnotationExceptionHandlerMethodResolver(type);
				if (resolver.hasExceptionMappings()) {
					registerExceptionHandlerAdvice(bean, resolver);
					logger.info("Detected @MessageExceptionHandler methods in " + bean);
				}
			}
		}
	}
