		@PreDestroy
		private void destroy() {
			if (this.destroyCalled) {
				throw new IllegalStateException("Superclass destroy called too soon");
			}
			if (this.destroy3Called) {
				throw new IllegalStateException("Already called");
			}
			this.destroy3Called = true;
		}
