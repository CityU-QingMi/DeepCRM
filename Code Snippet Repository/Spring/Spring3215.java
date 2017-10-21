		@PostConstruct
		protected void init2() {
			if (this.testBean == null || this.testBean2 == null) {
				throw new IllegalStateException("Resources not injected");
			}
			if (!this.initCalled) {
				throw new IllegalStateException("Superclass init method not called yet");
			}
			if (this.init2Called) {
				throw new IllegalStateException("Already called");
			}
			this.init2Called = true;
		}
