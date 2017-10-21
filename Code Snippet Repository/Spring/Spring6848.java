	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof QosSettings)) {
			return false;
		}

		QosSettings otherSettings = (QosSettings) other;
		return (this.deliveryMode == otherSettings.deliveryMode &&
				this.priority == otherSettings.priority &&
				this.timeToLive == otherSettings.timeToLive);
	}
