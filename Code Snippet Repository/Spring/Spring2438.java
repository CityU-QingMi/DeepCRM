	private void unregisterNotificationListeners() {
		if (this.server != null) {
			this.registeredNotificationListeners.forEach((bean, mappedObjectNames) -> {
				for (ObjectName mappedObjectName : mappedObjectNames) {
					try {
						this.server.removeNotificationListener(mappedObjectName, bean.getNotificationListener(),
								bean.getNotificationFilter(), bean.getHandback());
					}
					catch (Throwable ex) {
						if (logger.isDebugEnabled()) {
							logger.debug("Unable to unregister NotificationListener", ex);
						}
					}
				}
			});
		}
		this.registeredNotificationListeners.clear();
	}
