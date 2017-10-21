		private Lock(long timeout, UUID sourceUuid, long lockId, Object version,
				long unlockTimestamp, int multiplicity, boolean concurrent) {
			this.sourceUuid = sourceUuid;
			this.lockId = lockId;
			this.version = version;

			this.timeout = timeout;
			this.unlockTimestamp = unlockTimestamp;
			this.multiplicity = multiplicity;
			this.concurrent = concurrent;
		}
