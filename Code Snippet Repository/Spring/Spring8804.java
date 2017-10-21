	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		try {
			return mi.proceed();
		}
		catch (RuntimeException ex) {
			// Let it throw raw if the type of the exception is on the throws clause of the method.
			if (!this.alwaysTranslate && ReflectionUtils.declaresException(mi.getMethod(), ex.getClass())) {
				throw ex;
			}
			else {
				PersistenceExceptionTranslator translator = this.persistenceExceptionTranslator;
				if (translator == null) {
					Assert.state(this.beanFactory != null, "No PersistenceExceptionTranslator set");
					translator = detectPersistenceExceptionTranslators(this.beanFactory);
					this.persistenceExceptionTranslator = translator;
				}
				throw DataAccessUtils.translateIfNecessary(ex, translator);
			}
		}
	}
