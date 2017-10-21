		@Bean @Lazy
		@Override
		public TestBean testBean() {
			return new TestBean() {
				@Override
				public String toString() {
					return "overridden";
				}
			};
		}
