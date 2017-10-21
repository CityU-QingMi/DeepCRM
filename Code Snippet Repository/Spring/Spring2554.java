	@SuppressWarnings("")
	public AsyncAnnotationAdvisor(@Nullable Executor executor, @Nullable AsyncUncaughtExceptionHandler exceptionHandler) {
		Set<Class<? extends Annotation>> asyncAnnotationTypes = new LinkedHashSet<>(2);
		asyncAnnotationTypes.add(Async.class);
		try {
			asyncAnnotationTypes.add((Class<? extends Annotation>)
					ClassUtils.forName("javax.ejb.Asynchronous", AsyncAnnotationAdvisor.class.getClassLoader()));
		}
		catch (ClassNotFoundException ex) {
			// If EJB 3.1 API not present, simply ignore.
		}
		if (exceptionHandler != null) {
			this.exceptionHandler = exceptionHandler;
		}
		else {
			this.exceptionHandler = new SimpleAsyncUncaughtExceptionHandler();
		}
		this.advice = buildAdvice(executor, this.exceptionHandler);
		this.pointcut = buildPointcut(asyncAnnotationTypes);
	}
