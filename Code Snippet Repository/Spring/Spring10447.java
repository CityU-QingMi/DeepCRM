	@Test
	public void testMultipartFileAsString() {
		TestBean target = new TestBean();
		WebRequestDataBinder binder = new WebRequestDataBinder(target);
		binder.registerCustomEditor(String.class, new StringMultipartFileEditor());

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		request.addFile(new MockMultipartFile("name", "Juergen".getBytes()));
		binder.bind(new ServletWebRequest(request));
		assertEquals("Juergen", target.getName());
	}
