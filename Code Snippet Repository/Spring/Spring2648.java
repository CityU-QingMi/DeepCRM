	protected void prepareScriptBeans(BeanDefinition bd, String scriptFactoryBeanName, String scriptedObjectBeanName) {
		// Avoid recreation of the script bean definition in case of a prototype.
		synchronized (this.scriptBeanFactory) {
			if (!this.scriptBeanFactory.containsBeanDefinition(scriptedObjectBeanName)) {

				this.scriptBeanFactory.registerBeanDefinition(
						scriptFactoryBeanName, createScriptFactoryBeanDefinition(bd));
				ScriptFactory scriptFactory =
						this.scriptBeanFactory.getBean(scriptFactoryBeanName, ScriptFactory.class);
				ScriptSource scriptSource =
						getScriptSource(scriptFactoryBeanName, scriptFactory.getScriptSourceLocator());
				Class<?>[] interfaces = scriptFactory.getScriptInterfaces();

				Class<?>[] scriptedInterfaces = interfaces;
				if (scriptFactory.requiresConfigInterface() && !bd.getPropertyValues().isEmpty()) {
					Class<?> configInterface = createConfigInterface(bd, interfaces);
					scriptedInterfaces = ObjectUtils.addObjectToArray(interfaces, configInterface);
				}

				BeanDefinition objectBd = createScriptedObjectBeanDefinition(
						bd, scriptFactoryBeanName, scriptSource, scriptedInterfaces);
				long refreshCheckDelay = resolveRefreshCheckDelay(bd);
				if (refreshCheckDelay >= 0) {
					objectBd.setScope(BeanDefinition.SCOPE_PROTOTYPE);
				}

				this.scriptBeanFactory.registerBeanDefinition(scriptedObjectBeanName, objectBd);
			}
		}
	}
