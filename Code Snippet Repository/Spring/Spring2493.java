	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NotificationListenerHolder)) {
			return false;
		}
		NotificationListenerHolder otherNlh = (NotificationListenerHolder) other;
		return (ObjectUtils.nullSafeEquals(this.notificationListener, otherNlh.notificationListener) &&
				ObjectUtils.nullSafeEquals(this.notificationFilter, otherNlh.notificationFilter) &&
				ObjectUtils.nullSafeEquals(this.handback, otherNlh.handback) &&
				ObjectUtils.nullSafeEquals(this.mappedObjectNames, otherNlh.mappedObjectNames));
	}
