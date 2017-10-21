	@Bean(name = TaskManagementConfigUtils.ASYNC_EXECUTION_ASPECT_BEAN_NAME)
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	public AnnotationAsyncExecutionAspect asyncAdvisor() {
		AnnotationAsyncExecutionAspect asyncAspect = AnnotationAsyncExecutionAspect.aspectOf();
		if (this.executor != null) {
			asyncAspect.setExecutor(this.executor);
		}
		if (this.exceptionHandler != null) {
			asyncAspect.setExceptionHandler(this.exceptionHandler);
		}
		return asyncAspect;
	}
