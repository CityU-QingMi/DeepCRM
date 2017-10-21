		@Override
		public boolean isUnlockable(SoftLock lock) {
			if ( lock == this ) {
				return true;
			}
			else if ( lock instanceof Lock ) {
				return (lockId == ((Lock) lock).lockId) && sourceUuid.equals(((Lock) lock).sourceUuid);
			}
			else {
				return false;
			}
		}
