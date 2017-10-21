	public AbstractBeanDefinition bean(Class<?> type, Object...args) {
		GroovyBeanDefinitionWrapper current = this.currentBeanDefinition;
		try {
			Closure callable = null;
			Collection constructorArgs = null;
			if (!ObjectUtils.isEmpty(args)) {
				int index = args.length;
				Object lastArg = args[index - 1];
				if (lastArg instanceof Closure) {
					callable = (Closure) lastArg;
					index--;
				}
				if (index > -1) {
					constructorArgs = resolveConstructorArguments(args, 0, index);
				}
			}
			this.currentBeanDefinition = new GroovyBeanDefinitionWrapper(null, type, constructorArgs);
			if (callable != null) {
				callable.call(this.currentBeanDefinition);
			}
			return this.currentBeanDefinition.getBeanDefinition();

		}
		finally {
			this.currentBeanDefinition = current;
		}
	}
