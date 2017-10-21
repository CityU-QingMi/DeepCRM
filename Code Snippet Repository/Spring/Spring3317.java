	@Test
	public void twoLevelsInLiteMode() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(L0ConfigLight.class);
		ctx.refresh();

		assertFalse(ctx.getBeanFactory().containsSingleton("nestedConfigurationClassTests.L0ConfigLight"));
		ctx.getBean(L0ConfigLight.class);
		ctx.getBean("l0Bean");

		assertTrue(ctx.getBeanFactory().containsSingleton(L0ConfigLight.L1ConfigLight.class.getName()));
		ctx.getBean(L0ConfigLight.L1ConfigLight.class);
		ctx.getBean("l1Bean");

		assertFalse(ctx.getBeanFactory().containsSingleton(L0ConfigLight.L1ConfigLight.L2ConfigLight.class.getName()));
		ctx.getBean(L0ConfigLight.L1ConfigLight.L2ConfigLight.class);
		ctx.getBean("l2Bean");

		// ensure that override order is correct
		assertThat(ctx.getBean("overrideBean", TestBean.class).getName(), is("override-l0"));
	}
