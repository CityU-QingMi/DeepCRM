	@Test
	public void testMessageSourceAware() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(CONTEXT_WILDCARD);
		MessageSource messageSource = (MessageSource) ctx.getBean("messageSource");
		Service service1 = (Service) ctx.getBean("service");
		assertEquals(ctx, service1.getMessageSource());
		Service service2 = (Service) ctx.getBean("service2");
		assertEquals(ctx, service2.getMessageSource());
		AutowiredService autowiredService1 = (AutowiredService) ctx.getBean("autowiredService");
		assertEquals(messageSource, autowiredService1.getMessageSource());
		AutowiredService autowiredService2 = (AutowiredService) ctx.getBean("autowiredService2");
		assertEquals(messageSource, autowiredService2.getMessageSource());
		ctx.close();
	}
