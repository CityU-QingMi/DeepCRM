	private void initControllerAdviceCache() {
		ApplicationContext context = getApplicationContext();
		if (context == null) {
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Looking for @MessageExceptionHandler mappings: " + context);
		}
		List<ControllerAdviceBean> beans = ControllerAdviceBean.findAnnotatedBeans(context);
		AnnotationAwareOrderComparator.sort(beans);
		initMessagingAdviceCache(MessagingControllerAdviceBean.createFromList(beans));
	}
