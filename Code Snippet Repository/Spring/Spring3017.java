		@Override
		public void afterPropertiesSet() {
			if (this.initMethodInvoked) {
				fail();
			}
			if (this.afterPropertiesSetInvoked) {
				throw new IllegalStateException("Already initialized");
			}
			this.afterPropertiesSetInvoked = true;
		}
