	public void addTransformer(ClassFileTransformer transformer) {
		Assert.notNull(transformer, "ClassFileTransformer must not be null");
		try {
			InvocationHandler adapter = new WebLogicClassPreProcessorAdapter(transformer, this.classLoader);
			Object adapterInstance = Proxy.newProxyInstance(this.wlPreProcessorClass.getClassLoader(),
					new Class<?>[] {this.wlPreProcessorClass}, adapter);
			this.addPreProcessorMethod.invoke(this.classLoader, adapterInstance);
		}
		catch (InvocationTargetException ex) {
			throw new IllegalStateException("WebLogic addInstanceClassPreProcessor method threw exception", ex.getCause());
		}
		catch (Throwable ex) {
			throw new IllegalStateException("Could not invoke WebLogic addInstanceClassPreProcessor method", ex);
		}
	}
