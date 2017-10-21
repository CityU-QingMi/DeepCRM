	public void sendNotification(String eventType, Object data, String msg) {
		final Notification notification = new Notification(
				eventType,
				this,
				sequenceNumber.incrementAndGet(),
				System.currentTimeMillis(),
				msg
		);
		if ( data != null ) {
			notification.setUserData( data );
		}
		emitter.sendNotification( notification );
	}
