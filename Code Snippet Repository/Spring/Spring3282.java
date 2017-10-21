		@Bean
		@Scope("")
		public Repository<String> stringRepo() {
			return new Repository<String>() {
				@Override
				public String toString() {
					return "Repository<String>";
				}
			};
		}
