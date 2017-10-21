	private void shutdownGlobalResources() {
		try {
			Method method = TcpResources.class.getDeclaredMethod("_dispose");
			ReflectionUtils.makeAccessible(method);
			ReflectionUtils.invokeMethod(method, TcpResources.get());
		}
		catch (NoSuchMethodException ex) {
			// ignore
		}
	}
