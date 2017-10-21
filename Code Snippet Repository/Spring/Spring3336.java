		@Bean
		public FactoryBean<Foo> foo() {
			return new FactoryBean<Foo>() {
				@Override
				public Foo getObject() {
					return new Foo("x");
				}
				@Override
				public Class<?> getObjectType() {
					return Foo.class;
				}
			};
		}
