	@Test
	public void testAliasForParentContext() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(FQ_SIMPLE_CONTEXT);
		assertTrue(ctx.containsBean("someMessageSource"));

		ClassPathXmlApplicationContext child = new ClassPathXmlApplicationContext(
				new String[] {ALIAS_FOR_PARENT_CONTEXT}, ctx);
		assertTrue(child.containsBean("someMessageSource"));
		assertTrue(child.containsBean("yourMessageSource"));
		assertTrue(child.containsBean("myMessageSource"));
		assertTrue(child.isSingleton("someMessageSource"));
		assertTrue(child.isSingleton("yourMessageSource"));
		assertTrue(child.isSingleton("myMessageSource"));
		assertEquals(StaticMessageSource.class, child.getType("someMessageSource"));
		assertEquals(StaticMessageSource.class, child.getType("yourMessageSource"));
		assertEquals(StaticMessageSource.class, child.getType("myMessageSource"));

		Object someMs = child.getBean("someMessageSource");
		Object yourMs = child.getBean("yourMessageSource");
		Object myMs = child.getBean("myMessageSource");
		assertSame(someMs, yourMs);
		assertSame(someMs, myMs);

		String[] aliases = child.getAliases("someMessageSource");
		assertEquals(2, aliases.length);
		assertEquals("myMessageSource", aliases[0]);
		assertEquals("yourMessageSource", aliases[1]);
		aliases = child.getAliases("myMessageSource");
		assertEquals(2, aliases.length);
		assertEquals("someMessageSource", aliases[0]);
		assertEquals("yourMessageSource", aliases[1]);

		child.close();
		ctx.close();
	}
