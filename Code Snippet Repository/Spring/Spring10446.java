	@Test
	public void testEnumBinding() {
		EnumHolder target = new EnumHolder();
		WebRequestDataBinder binder = new WebRequestDataBinder(target);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("myEnum", "FOO");
		binder.bind(new ServletWebRequest(request));
		assertEquals(MyEnum.FOO, target.getMyEnum());
	}
