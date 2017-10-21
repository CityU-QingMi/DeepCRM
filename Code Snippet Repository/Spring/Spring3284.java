		@Bean
		@Scope("")
		@SuppressWarnings("")
		public Repository genericRepo() {
			return new Repository<Object>() {
				@Override
				public String toString() {
					return "Repository<Object>";
				}
			};
		}
