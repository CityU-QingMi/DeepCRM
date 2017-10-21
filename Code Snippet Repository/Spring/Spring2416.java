	public ClassLoader getThrowawayClassLoader() {
		try {
			ClassLoader loader = this.cloneConstructor.newInstance(getClassLoader());
			// Clear out the transformers (copied as well)
			List<?> list = (List<?>) this.transformerList.get(loader);
			list.clear();
			return loader;
		}
		catch (InvocationTargetException ex) {
			throw new IllegalStateException("WebSphere CompoundClassLoader constructor failed", ex.getCause());
		}
		catch (Throwable ex) {
			throw new IllegalStateException("Could not construct WebSphere CompoundClassLoader", ex);
		}
	}
