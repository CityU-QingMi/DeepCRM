	@Override
	public boolean isCompilable() {
		CachedMethodExecutor executorToCheck = this.cachedExecutor;
		if (executorToCheck == null || !(executorToCheck.get() instanceof ReflectiveMethodExecutor)) {
			return false;
		}

		for (SpelNodeImpl child : this.children) {
			if (!child.isCompilable()) {
				return false;
			}
		}

		ReflectiveMethodExecutor executor = (ReflectiveMethodExecutor) executorToCheck.get();
		if (executor.didArgumentConversionOccur()) {
			return false;
		}
		Method method = executor.getMethod();
		Class<?> clazz = method.getDeclaringClass();
		if (!Modifier.isPublic(clazz.getModifiers()) && executor.getPublicDeclaringClass() == null) {
			return false;
		}

		return true;
	}
