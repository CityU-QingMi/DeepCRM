		@Override
		public long nextBackOff() {
			if (this.currentElapsedTime >= maxElapsedTime) {
				return STOP;
			}

			long nextInterval = computeNextInterval();
			this.currentElapsedTime += nextInterval;
			return nextInterval;
		}
