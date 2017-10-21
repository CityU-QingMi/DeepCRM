	private void assertOneMessageSourceOnly(ClassPathXmlApplicationContext ctx, Object myMessageSource) {
		String[] beanNamesForType = ctx.getBeanNamesForType(StaticMessageSource.class);
		assertEquals(1, beanNamesForType.length);
		assertEquals("myMessageSource", beanNamesForType[0]);
		beanNamesForType = ctx.getBeanNamesForType(StaticMessageSource.class, true, true);
		assertEquals(1, beanNamesForType.length);
		assertEquals("myMessageSource", beanNamesForType[0]);
		beanNamesForType = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(ctx, StaticMessageSource.class);
		assertEquals(1, beanNamesForType.length);
		assertEquals("myMessageSource", beanNamesForType[0]);
		beanNamesForType = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(ctx, StaticMessageSource.class, true, true);
		assertEquals(1, beanNamesForType.length);
		assertEquals("myMessageSource", beanNamesForType[0]);

		Map<?, StaticMessageSource> beansOfType = ctx.getBeansOfType(StaticMessageSource.class);
		assertEquals(1, beansOfType.size());
		assertSame(myMessageSource, beansOfType.values().iterator().next());
		beansOfType = ctx.getBeansOfType(StaticMessageSource.class, true, true);
		assertEquals(1, beansOfType.size());
		assertSame(myMessageSource, beansOfType.values().iterator().next());
		beansOfType = BeanFactoryUtils.beansOfTypeIncludingAncestors(ctx, StaticMessageSource.class);
		assertEquals(1, beansOfType.size());
		assertSame(myMessageSource, beansOfType.values().iterator().next());
		beansOfType = BeanFactoryUtils.beansOfTypeIncludingAncestors(ctx, StaticMessageSource.class, true, true);
		assertEquals(1, beansOfType.size());
		assertSame(myMessageSource, beansOfType.values().iterator().next());
	}
