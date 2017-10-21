		public Lock unlock(long timestamp) {
			if (multiplicity == 1) {
				return new Lock(timeout, sourceUuid, lockId, version,
						timestamp, 0, concurrent );

			}
			else {
				return new Lock(timeout, sourceUuid, lockId, version,
						0, multiplicity - 1, concurrent );
			}
		}
