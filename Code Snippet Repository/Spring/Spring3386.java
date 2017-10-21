	@Test
	public void testInjection() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TestContext.class);
		CustomCondition condition = ctx.getBean(CustomCondition.class);

		condition.setCondition(true);
		FirstService firstService = (FirstService) ctx.getBean(Service.class);
		assertNotNull("FirstService.dependency is null", firstService.getDependency());

		condition.setCondition(false);
		SecondService secondService = (SecondService) ctx.getBean(Service.class);
		assertNotNull("SecondService.dependency is null", secondService.getDependency());
	}
