		@Bean
		public FactoryBean testBean() {
			final String name = env.getProperty("testbean.name");
			return new FactoryBean() {
				@Override
				public Object getObject() {
					return new TestBean(name);
				}
				@Override
				public Class<?> getObjectType() {
					return TestBean.class;
				}
				@Override
				public boolean isSingleton() {
					return false;
				}
			};
		}
