	private static RequestUpgradeStrategy initUpgradeStrategy() {
		String className;
		if (tomcatPresent) {
			className = "TomcatRequestUpgradeStrategy";
		}
		else if (jettyPresent) {
			className = "JettyRequestUpgradeStrategy";
		}
		else if (undertowPresent) {
			className = "UndertowRequestUpgradeStrategy";
		}
		else if (reactorNettyPresent) {
			// As late as possible (Reactor Netty commonly used for WebClient)
			className = "ReactorNettyRequestUpgradeStrategy";
		}
		else {
			throw new IllegalStateException("No suitable default RequestUpgradeStrategy found");
		}

		try {
			className = "org.springframework.web.reactive.socket.server.upgrade." + className;
			Class<?> clazz = ClassUtils.forName(className, HandshakeWebSocketService.class.getClassLoader());
			return (RequestUpgradeStrategy) ReflectionUtils.accessibleConstructor(clazz).newInstance();
		}
		catch (Throwable ex) {
			throw new IllegalStateException(
					"Failed to instantiate RequestUpgradeStrategy: " + className, ex);
		}
	}
