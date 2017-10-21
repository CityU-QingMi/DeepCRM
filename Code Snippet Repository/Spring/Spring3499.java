	@Test
	public void testAliasThatOverridesParent() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(FQ_SIMPLE_CONTEXT);
		Object someMs = ctx.getBean("someMessageSource");

		ClassPathXmlApplicationContext child = new ClassPathXmlApplicationContext(
				new String[] {ALIAS_THAT_OVERRIDES_PARENT_CONTEXT}, ctx);
		Object myMs = child.getBean("myMessageSource");
		Object someMs2 = child.getBean("someMessageSource");
		assertSame(myMs, someMs2);
		assertNotSame(someMs, someMs2);
		assertOneMessageSourceOnly(child, myMs);
	}
