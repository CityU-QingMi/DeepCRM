	@Test
	public void testMultipartFileAsStringArray() {
		TestBean target = new TestBean();
		WebRequestDataBinder binder = new WebRequestDataBinder(target);
		binder.registerCustomEditor(String.class, new StringMultipartFileEditor());

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		request.addFile(new MockMultipartFile("stringArray", "Juergen".getBytes()));
		binder.bind(new ServletWebRequest(request));
		assertEquals(1, target.getStringArray().length);
		assertEquals("Juergen", target.getStringArray()[0]);
	}
