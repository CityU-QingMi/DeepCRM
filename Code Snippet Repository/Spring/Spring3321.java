	@Test
	public void twoLevelsWithNoBeanMethods() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(L0ConfigEmpty.class);
		ctx.refresh();

		assertFalse(ctx.getBeanFactory().containsSingleton("l0ConfigEmpty"));
		Object l0i1 = ctx.getBean(L0ConfigEmpty.class);
		Object l0i2 = ctx.getBean(L0ConfigEmpty.class);
		assertTrue(l0i1 == l0i2);

		Object l1i1 = ctx.getBean(L0ConfigEmpty.L1ConfigEmpty.class);
		Object l1i2 = ctx.getBean(L0ConfigEmpty.L1ConfigEmpty.class);
		assertTrue(l1i1 != l1i2);

		Object l2i1 = ctx.getBean(L0ConfigEmpty.L1ConfigEmpty.L2ConfigEmpty.class);
		Object l2i2 = ctx.getBean(L0ConfigEmpty.L1ConfigEmpty.L2ConfigEmpty.class);
		assertTrue(l2i1 == l2i2);
		assertNotEquals(l2i1.toString(), l2i2.toString());
	}
