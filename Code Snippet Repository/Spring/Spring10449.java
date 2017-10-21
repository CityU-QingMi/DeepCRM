	@Test
	public void testMultipartFilesAsStringArray() {
		TestBean target = new TestBean();
		WebRequestDataBinder binder = new WebRequestDataBinder(target);
		binder.registerCustomEditor(String.class, new StringMultipartFileEditor());

		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
		request.addFile(new MockMultipartFile("stringArray", "Juergen".getBytes()));
		request.addFile(new MockMultipartFile("stringArray", "Eva".getBytes()));
		binder.bind(new ServletWebRequest(request));
		assertEquals(2, target.getStringArray().length);
		assertEquals("Juergen", target.getStringArray()[0]);
		assertEquals("Eva", target.getStringArray()[1]);
	}
