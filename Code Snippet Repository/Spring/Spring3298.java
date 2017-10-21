		@Bean(destroyMethod = "")
		public WithInheritedCloseMethod c5() {
			return new WithInheritedCloseMethod() {
				@Override
				public void close() throws IOException {
					throw new IllegalStateException("close() should not be called");
				}
				@SuppressWarnings("unused")
				public void other() {
					this.closed = true;
				}
			};
		}
