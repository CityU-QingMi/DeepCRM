	@Override
	public boolean matches(Method method, @Nullable Class<?> targetClass, Object... args) {
		this.evaluations++;

		for (StackTraceElement element : new Throwable().getStackTrace()) {
			if (element.getClassName().equals(this.clazz.getName()) &&
					(this.methodName == null || element.getMethodName().equals(this.methodName))) {
				return true;
			}
		}
		return false;
	}
