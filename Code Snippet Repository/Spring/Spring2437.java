	private void registerNotificationListeners() throws MBeanExportException {
		if (this.notificationListeners != null) {
			Assert.state(this.server != null, "No MBeanServer available");
			for (NotificationListenerBean bean : this.notificationListeners) {
				try {
					ObjectName[] mappedObjectNames = bean.getResolvedObjectNames();
					if (mappedObjectNames == null) {
						// Mapped to all MBeans registered by the MBeanExporter.
						mappedObjectNames = getRegisteredObjectNames();
					}
					if (this.registeredNotificationListeners.put(bean, mappedObjectNames) == null) {
						for (ObjectName mappedObjectName : mappedObjectNames) {
							this.server.addNotificationListener(mappedObjectName, bean.getNotificationListener(),
									bean.getNotificationFilter(), bean.getHandback());
						}
					}
				}
				catch (Throwable ex) {
					throw new MBeanExportException("Unable to register NotificationListener", ex);
				}
			}
		}
	}
