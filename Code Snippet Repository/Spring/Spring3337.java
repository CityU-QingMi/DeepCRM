		@Bean
		public FactoryBean<Foo> foo() {
			return new AbstractFactoryBean<Foo>() {
				@Override
				public Foo createInstance() {
					return new Foo("x");
				}
				@Override
				public Class<?> getObjectType() {
					return Foo.class;
				}
			};
		}
