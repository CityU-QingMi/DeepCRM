		@Bean
		public FactoryBean<FooInterface> foo() {
			return new AbstractFactoryBean<FooInterface>() {
				@Override
				public FooInterface createInstance() {
					return new Foo("x");
				}
				@Override
				public Class<?> getObjectType() {
					return FooInterface.class;
				}
			};
		}
