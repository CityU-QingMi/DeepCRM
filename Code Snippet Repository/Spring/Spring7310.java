	public void setHeartbeat(@Nullable long[] heartbeat) {
		if (heartbeat == null || heartbeat.length != 2) {
			throw new IllegalArgumentException("Heart-beat array must be of length 2, not " +
					(heartbeat != null ? heartbeat.length : "null"));
		}
		String value = heartbeat[0] + "," + heartbeat[1];
		if (heartbeat[0] < 0 || heartbeat[1] < 0) {
			throw new IllegalArgumentException("Heart-beat values cannot be negative: " + value);
		}
		set(HEARTBEAT, value);
	}
