	@Test
	public void resolveTestContextBootstrapperWithDoubleMetaBootstrapWithAnnotation() {
		BootstrapContext bootstrapContext = BootstrapTestUtils.buildBootstrapContext(
			DoubleMetaAnnotatedBootstrapWithAnnotationClass.class, delegate);

		exception.expect(IllegalStateException.class);
		exception.expectMessage(containsString("found multiple declarations of @BootstrapWith"));
		exception.expectMessage(containsString(FooBootstrapper.class.getName()));
		exception.expectMessage(containsString(BarBootstrapper.class.getName()));

		resolveTestContextBootstrapper(bootstrapContext);
	}
