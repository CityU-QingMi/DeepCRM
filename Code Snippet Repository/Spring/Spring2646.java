	@Override
	@Nullable
	public Class<?> predictBeanType(Class<?> beanClass, String beanName) {
		// We only apply special treatment to ScriptFactory implementations here.
		if (!ScriptFactory.class.isAssignableFrom(beanClass)) {
			return null;
		}

		Assert.state(this.beanFactory != null, "No BeanFactory set");
		BeanDefinition bd = this.beanFactory.getMergedBeanDefinition(beanName);

		try {
			String scriptFactoryBeanName = SCRIPT_FACTORY_NAME_PREFIX + beanName;
			String scriptedObjectBeanName = SCRIPTED_OBJECT_NAME_PREFIX + beanName;
			prepareScriptBeans(bd, scriptFactoryBeanName, scriptedObjectBeanName);

			ScriptFactory scriptFactory = this.scriptBeanFactory.getBean(scriptFactoryBeanName, ScriptFactory.class);
			ScriptSource scriptSource = getScriptSource(scriptFactoryBeanName, scriptFactory.getScriptSourceLocator());
			Class<?>[] interfaces = scriptFactory.getScriptInterfaces();

			Class<?> scriptedType = scriptFactory.getScriptedObjectType(scriptSource);
			if (scriptedType != null) {
				return scriptedType;
			}
			else if (!ObjectUtils.isEmpty(interfaces)) {
				return (interfaces.length == 1 ? interfaces[0] : createCompositeInterface(interfaces));
			}
			else {
				if (bd.isSingleton()) {
					return this.scriptBeanFactory.getBean(scriptedObjectBeanName).getClass();
				}
			}
		}
		catch (Exception ex) {
			if (ex instanceof BeanCreationException &&
					((BeanCreationException) ex).getMostSpecificCause() instanceof BeanCurrentlyInCreationException) {
				if (logger.isTraceEnabled()) {
					logger.trace("Could not determine scripted object type for bean '" + beanName + "': "
							+ ex.getMessage());
				}
			}
			else {
				if (logger.isDebugEnabled()) {
					logger.debug("Could not determine scripted object type for bean '" + beanName + "'", ex);
				}
			}
		}

		return null;
	}
