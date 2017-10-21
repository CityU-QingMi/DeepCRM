	@SuppressWarnings("")
	@Nullable
	public static <T> T invokeMethod(Object target, String name, Object... args) {
		Assert.notNull(target, "Target object must not be null");
		Assert.hasText(name, "Method name must not be empty");

		try {
			MethodInvoker methodInvoker = new MethodInvoker();
			methodInvoker.setTargetObject(target);
			methodInvoker.setTargetMethod(name);
			methodInvoker.setArguments(args);
			methodInvoker.prepare();

			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Invoking method '%s' on %s with arguments %s", name, safeToString(target),
						ObjectUtils.nullSafeToString(args)));
			}

			return (T) methodInvoker.invoke();
		}
		catch (Exception ex) {
			ReflectionUtils.handleReflectionException(ex);
			throw new IllegalStateException("Should never get here");
		}
	}
