		private boolean isExpired(Instant currentTime) {
			if (this.state.get().equals(State.EXPIRED)) {
				return true;
			}
			if (checkExpired(currentTime)) {
				this.state.set(State.EXPIRED);
				return true;
			}
			return false;
		}
