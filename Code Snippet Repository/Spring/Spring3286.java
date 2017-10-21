		@Bean
		@PrototypeScoped
		public Repository<Integer> integerRepo() {
			return new Repository<Integer>() {
				@Override
				public String toString() {
					return "Repository<Integer>";
				}
			};
		}
