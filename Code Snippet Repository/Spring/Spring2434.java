	public void setNotificationListenerMappings(Map<?, ? extends NotificationListener> listeners) {
		Assert.notNull(listeners, "'listeners' must not be null");
		List<NotificationListenerBean> notificationListeners =
				new ArrayList<>(listeners.size());

		listeners.forEach((key, listener) -> {
			// Get the listener from the map value.
			NotificationListenerBean bean = new NotificationListenerBean(listener);
			// Get the ObjectName from the map key.
			if (key != null && !WILDCARD.equals(key)) {
				// This listener is mapped to a specific ObjectName.
				bean.setMappedObjectName(key);
			}
			notificationListeners.add(bean);
		});

		this.notificationListeners =
				notificationListeners.toArray(new NotificationListenerBean[notificationListeners.size()]);
	}
