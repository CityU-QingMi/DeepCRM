	@Test
	public void createWithoutJobDetail() throws ParseException {
		SimpleTriggerFactoryBean factory = new SimpleTriggerFactoryBean();
		factory.setName("myTrigger");
		factory.setRepeatCount(5);
		factory.setRepeatInterval(1000L);
		factory.afterPropertiesSet();
		SimpleTrigger trigger = factory.getObject();
		assertEquals(5, trigger.getRepeatCount());
		assertEquals(1000L, trigger.getRepeatInterval());
	}
