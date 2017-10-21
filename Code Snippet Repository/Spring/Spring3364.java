	@Test
	public void configurationWithAdaptivePrototypes() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ConfigWithPrototypeBean.class, AdaptiveInjectionPoints.class);
		ctx.refresh();

		AdaptiveInjectionPoints adaptive = ctx.getBean(AdaptiveInjectionPoints.class);
		assertEquals("adaptiveInjectionPoint1", adaptive.adaptiveInjectionPoint1.getName());
		assertEquals("setAdaptiveInjectionPoint2", adaptive.adaptiveInjectionPoint2.getName());

		adaptive = ctx.getBean(AdaptiveInjectionPoints.class);
		assertEquals("adaptiveInjectionPoint1", adaptive.adaptiveInjectionPoint1.getName());
		assertEquals("setAdaptiveInjectionPoint2", adaptive.adaptiveInjectionPoint2.getName());
		ctx.close();
	}
