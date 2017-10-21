		@Bean
		@Scope(scopeName = "", proxyMode = ScopedProxyMode.TARGET_CLASS)
		public Repository<String> stringRepo() {
			return new Repository<String>() {
				@Override
				public String toString() {
					return "Repository<String>";
				}
			};
		}
