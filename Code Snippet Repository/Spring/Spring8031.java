	@Override
	public boolean supports(Class<?> clazz) {
		Assert.notNull(clazz, "Class must not be null");
		if (this.targetClass != null) {
			return (this.targetClass == clazz);
		}
		Assert.state(this.bindingFactory != null, "JibxMarshaller not initialized");
		String[] mappedClasses = this.bindingFactory.getMappedClasses();
		String className = clazz.getName();
		for (String mappedClass : mappedClasses) {
			if (className.equals(mappedClass)) {
				return true;
			}
		}
		return false;
	}
