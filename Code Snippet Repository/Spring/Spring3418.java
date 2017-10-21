	@Test
	public void methodSignatureNoEvent() {
		AnnotationConfigApplicationContext failingContext =
				new AnnotationConfigApplicationContext();
		failingContext.register(BasicConfiguration.class,
				InvalidMethodSignatureEventListener.class);

		this.thrown.expect(BeanInitializationException.class);
		this.thrown.expectMessage(InvalidMethodSignatureEventListener.class.getName());
		this.thrown.expectMessage("cannotBeCalled");
		failingContext.refresh();
	}
