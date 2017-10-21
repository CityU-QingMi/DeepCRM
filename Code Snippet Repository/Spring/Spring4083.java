	@Test
	public void testCallSetMessageCodesResolverTwice() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("DataBinder is already initialized with MessageCodesResolver");

		TestBean testBean = new TestBean();
		DataBinder binder = new DataBinder(testBean, "testBean");
		binder.setMessageCodesResolver(new DefaultMessageCodesResolver());
		binder.setMessageCodesResolver(new DefaultMessageCodesResolver());

	}
