	@Test
	public void transactionProxyIsCreatedWithEnableOnExcludedSuperclass() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				ParentEnableTxConfig.class, ChildEnableTxConfig.class, TxManagerConfig.class);
		TransactionalTestBean bean = ctx.getBean(TransactionalTestBean.class);
		assertTrue("testBean is not a proxy", AopUtils.isAopProxy(bean));
		Map<?,?> services = ctx.getBeansWithAnnotation(Service.class);
		assertTrue("Stereotype annotation not visible", services.containsKey("testBean"));
		ctx.close();
	}
