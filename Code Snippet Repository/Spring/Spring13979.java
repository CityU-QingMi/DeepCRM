	@Override
	protected boolean validateRequest(String serverId, String sessionId, String transport) {
		if (!super.validateRequest(serverId, sessionId, transport)) {
			return false;
		}

		if (!this.allowedOrigins.contains("*")) {
			TransportType transportType = TransportType.fromValue(transport);
			if (transportType == null || !transportType.supportsOrigin()) {
				if (logger.isWarnEnabled()) {
					logger.warn("Origin check enabled but transport '" + transport + "' does not support it.");
				}
				return false;
			}
		}

		return true;
	}
