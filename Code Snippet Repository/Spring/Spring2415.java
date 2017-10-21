	public void addTransformer(ClassFileTransformer transformer) {
		Assert.notNull(transformer, "ClassFileTransformer must not be null");
		try {
			InvocationHandler adapter = new WebSphereClassPreDefinePlugin(transformer);
			Object adapterInstance = Proxy.newProxyInstance(this.wsPreProcessorClass.getClassLoader(),
					new Class<?>[] {this.wsPreProcessorClass}, adapter);
			this.addPreDefinePlugin.invoke(this.classLoader, adapterInstance);
		}
		catch (InvocationTargetException ex) {
			throw new IllegalStateException("WebSphere addPreDefinePlugin method threw exception", ex.getCause());
		}
		catch (Throwable ex) {
			throw new IllegalStateException("Could not invoke WebSphere addPreDefinePlugin method", ex);
		}
	}
