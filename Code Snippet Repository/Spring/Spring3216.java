		@PreDestroy
		protected void destroy2() {
			if (this.destroyCalled) {
				throw new IllegalStateException("Superclass destroy called too soon");
			}
			if (this.destroy2Called) {
				throw new IllegalStateException("Already called");
			}
			this.destroy2Called = true;
		}
