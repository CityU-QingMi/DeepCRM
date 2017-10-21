	@Test
	public void resolveBeanReference() throws Exception {
		StaticApplicationContext applicationContext = new StaticApplicationContext();
		BeanDefinition beanDefinition = new RootBeanDefinition(String.class);
		applicationContext.registerBeanDefinition("myBean", beanDefinition);
		applicationContext.refresh();

		EvaluationContext context = createEvaluationContext(CacheOperationExpressionEvaluator.NO_RESULT, applicationContext);
		Object value = new SpelExpressionParser().parseExpression("@myBean.class.getName()").getValue(context);
		assertThat(value, is(String.class.getName()));
	}
