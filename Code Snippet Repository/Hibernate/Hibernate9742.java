	private void removeAllNotificationListeners() {
		for ( NotificationListener listener : notificationListeners ) {
			try {
				emitter.removeNotificationListener( listener );
			}
			catch (ListenerNotFoundException e) {
				// ignore
			}
		}
		notificationListeners.clear();
	}
