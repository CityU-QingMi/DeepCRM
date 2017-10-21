	@Override
	public ClassLoader getThrowawayClassLoader() {
		if (this.getThrowawayClassLoaderMethod != null) {
			ClassLoader target = (ClassLoader)
					ReflectionUtils.invokeMethod(this.getThrowawayClassLoaderMethod, this.classLoader);
			return (target instanceof DecoratingClassLoader ? target :
					new OverridingClassLoader(this.classLoader, target));
		}
		else {
			return new SimpleThrowawayClassLoader(this.classLoader);
		}
	}
