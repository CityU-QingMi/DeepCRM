	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
		// We only apply special treatment to ScriptFactory implementations here.
		if (!ScriptFactory.class.isAssignableFrom(beanClass)) {
			return null;
		}

		Assert.state(this.beanFactory != null, "No BeanFactory set");
		BeanDefinition bd = this.beanFactory.getMergedBeanDefinition(beanName);
		String scriptFactoryBeanName = SCRIPT_FACTORY_NAME_PREFIX + beanName;
		String scriptedObjectBeanName = SCRIPTED_OBJECT_NAME_PREFIX + beanName;
		prepareScriptBeans(bd, scriptFactoryBeanName, scriptedObjectBeanName);

		ScriptFactory scriptFactory = this.scriptBeanFactory.getBean(scriptFactoryBeanName, ScriptFactory.class);
		ScriptSource scriptSource = getScriptSource(scriptFactoryBeanName, scriptFactory.getScriptSourceLocator());
		boolean isFactoryBean = false;
		try {
			Class<?> scriptedObjectType = scriptFactory.getScriptedObjectType(scriptSource);
			// Returned type may be null if the factory is unable to determine the type.
			if (scriptedObjectType != null) {
				isFactoryBean = FactoryBean.class.isAssignableFrom(scriptedObjectType);
			}
		}
		catch (Exception ex) {
			throw new BeanCreationException(beanName,
					"Could not determine scripted object type for " + scriptFactory, ex);
		}

		long refreshCheckDelay = resolveRefreshCheckDelay(bd);
		if (refreshCheckDelay >= 0) {
			Class<?>[] interfaces = scriptFactory.getScriptInterfaces();
			RefreshableScriptTargetSource ts = new RefreshableScriptTargetSource(this.scriptBeanFactory,
					scriptedObjectBeanName, scriptFactory, scriptSource, isFactoryBean);
			boolean proxyTargetClass = resolveProxyTargetClass(bd);
			String language = (String) bd.getAttribute(LANGUAGE_ATTRIBUTE);
			if (proxyTargetClass && (language == null || !language.equals("groovy"))) {
				throw new BeanDefinitionValidationException(
						"Cannot use proxyTargetClass=true with script beans where language is not 'groovy': '" +
						language + "'");
			}
			ts.setRefreshCheckDelay(refreshCheckDelay);
			return createRefreshableProxy(ts, interfaces, proxyTargetClass);
		}

		if (isFactoryBean) {
			scriptedObjectBeanName = BeanFactory.FACTORY_BEAN_PREFIX + scriptedObjectBeanName;
		}
		return this.scriptBeanFactory.getBean(scriptedObjectBeanName);
	}
