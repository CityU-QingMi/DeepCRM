	@Override
	public String toString() {
		return getClass().getSimpleName()
				+ '{' + "cache=" + cacheName
				+ ", strategy=" + evictionStrategy
				+ ", wakeUpInterval=" + evictionWakeUpInterval
				+ ", maxEntries=" + evictionMaxEntries
				+ ", lifespan=" + expirationLifespan
				+ ", maxIdle=" + expirationMaxIdle
				+ '}';
	}
