		@Bean
		public FooService fooServiceImpl(final ApplicationContext context) {
			return new FooServiceImpl() {
				@Override
				public String foo(int id) {
					assertNotNull(AopContext.currentProxy());
					return super.foo(id);
				}
				@Override
				protected FooDao fooDao() {
					return context.getBean(FooDao.class);
				}
			};
		}
