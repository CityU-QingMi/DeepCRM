	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PeriodicTrigger)) {
			return false;
		}
		PeriodicTrigger other = (PeriodicTrigger) obj;
		return (this.fixedRate == other.fixedRate && this.initialDelay == other.initialDelay &&
				this.period == other.period);
	}
