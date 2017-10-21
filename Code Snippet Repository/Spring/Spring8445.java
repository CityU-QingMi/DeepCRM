	@Test
	public void missingDataSourceAndTxMgr() throws Exception {
		ApplicationContext ctx = mock(ApplicationContext.class);
		given(ctx.getResource(anyString())).willReturn(mock(Resource.class));
		given(ctx.getAutowireCapableBeanFactory()).willReturn(mock(AutowireCapableBeanFactory.class));

		Class<?> clazz = MissingDataSourceAndTxMgr.class;
		BDDMockito.<Class<?>> given(testContext.getTestClass()).willReturn(clazz);
		given(testContext.getTestMethod()).willReturn(clazz.getDeclaredMethod("foo"));
		given(testContext.getApplicationContext()).willReturn(ctx);

		assertExceptionContains("supply at least a DataSource or PlatformTransactionManager");
	}
