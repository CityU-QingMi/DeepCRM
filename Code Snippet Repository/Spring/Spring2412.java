	public ClassLoader getThrowawayClassLoader() {
		try {
			Object classFinder = this.getClassFinderMethod.invoke(this.classLoader);
			Object parent = this.getParentMethod.invoke(this.classLoader);
			// arguments for 'clone'-like method
			return (ClassLoader) this.wlGenericClassLoaderConstructor.newInstance(classFinder, parent);
		}
		catch (InvocationTargetException ex) {
			throw new IllegalStateException("WebLogic GenericClassLoader constructor failed", ex.getCause());
		}
		catch (Throwable ex) {
			throw new IllegalStateException("Could not construct WebLogic GenericClassLoader", ex);
		}
	}
