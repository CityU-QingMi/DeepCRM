		@Bean @Lazy
		@Override
		public ExtendedTestBean testBean() {
			return new ExtendedTestBean() {
				@Override
				public String toString() {
					return "overridden";
				}
			};
		}
