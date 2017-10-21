		@Override
		public void destroy() {
			if (this.customDestroyed) {
				fail();
			}
			if (this.destroyed) {
				throw new IllegalStateException("Already destroyed");
			}
			this.destroyed = true;
		}
