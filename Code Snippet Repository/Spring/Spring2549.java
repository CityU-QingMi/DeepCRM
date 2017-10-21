	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		if (logger.isDebugEnabled()) {
			logger.debug("Incoming " + this.exporterNameClause + "remote call: " +
					ClassUtils.getQualifiedMethodName(method));
		}
		try {
			Object retVal = invocation.proceed();
			if (logger.isDebugEnabled()) {
				logger.debug("Finished processing of " + this.exporterNameClause + "remote call: " +
						ClassUtils.getQualifiedMethodName(method));
			}
			return retVal;
		}
		catch (Throwable ex) {
			if (ex instanceof RuntimeException || ex instanceof Error) {
				if (logger.isWarnEnabled()) {
					logger.warn("Processing of " + this.exporterNameClause + "remote call resulted in fatal exception: " +
							ClassUtils.getQualifiedMethodName(method), ex);
				}
			}
			else {
				if (logger.isInfoEnabled()) {
					logger.info("Processing of " + this.exporterNameClause + "remote call resulted in exception: " +
							ClassUtils.getQualifiedMethodName(method), ex);
				}
			}
			throw ex;
		}
	}
