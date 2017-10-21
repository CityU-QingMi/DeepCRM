	@Test
	public void enableLTW_withAjWeavingAutodetect() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(EnableLTWConfig_withAjWeavingAutodetect.class);
		ctx.refresh();
		LoadTimeWeaver loadTimeWeaver = ctx.getBean("loadTimeWeaver", LoadTimeWeaver.class);
		// no expectations -> a class file transformer should NOT be added
		// because no META-INF/aop.xml is present on the classpath
		verifyZeroInteractions(loadTimeWeaver);
	}
