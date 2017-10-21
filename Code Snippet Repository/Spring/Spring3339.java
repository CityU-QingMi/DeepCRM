		@Bean
		public AbstractFactoryBean<FooInterface> foo() {
			return new AbstractFactoryBean<FooInterface>() {
				@Override
				public FooInterface createInstance() {
					return new Foo("x");
				}
				@Override
				public Class<?> getObjectType() {
					return Foo.class;
				}
			};
		}
