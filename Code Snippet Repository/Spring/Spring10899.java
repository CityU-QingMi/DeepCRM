	private void doTestBinding(MockCommonsMultipartResolver resolver, MockHttpServletRequest originalRequest,
			MultipartHttpServletRequest request) throws UnsupportedEncodingException {

		MultipartTestBean1 mtb1 = new MultipartTestBean1();
		assertArrayEquals(null, mtb1.getField1());
		assertEquals(null, mtb1.getField2());
		ServletRequestDataBinder binder = new ServletRequestDataBinder(mtb1, "mybean");
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		binder.bind(request);
		List<MultipartFile> file1List = request.getFiles("field1");
		CommonsMultipartFile file1a = (CommonsMultipartFile) file1List.get(0);
		CommonsMultipartFile file1b = (CommonsMultipartFile) file1List.get(1);
		CommonsMultipartFile file2 = (CommonsMultipartFile) request.getFile("field2");
		assertEquals(file1a, mtb1.getField1()[0]);
		assertEquals(file1b, mtb1.getField1()[1]);
		assertEquals(new String(file2.getBytes()), new String(mtb1.getField2()));

		MultipartTestBean2 mtb2 = new MultipartTestBean2();
		assertArrayEquals(null, mtb2.getField1());
		assertEquals(null, mtb2.getField2());
		binder = new ServletRequestDataBinder(mtb2, "mybean");
		binder.registerCustomEditor(String.class, "field1", new StringMultipartFileEditor());
		binder.registerCustomEditor(String.class, "field2", new StringMultipartFileEditor("UTF-16"));
		binder.bind(request);
		assertEquals(new String(file1a.getBytes()), mtb2.getField1()[0]);
		assertEquals(new String(file1b.getBytes()), mtb2.getField1()[1]);
		assertEquals(new String(file2.getBytes(), "UTF-16"), mtb2.getField2());

		resolver.cleanupMultipart(request);
		assertTrue(((MockFileItem) file1a.getFileItem()).deleted);
		assertTrue(((MockFileItem) file1b.getFileItem()).deleted);
		assertTrue(((MockFileItem) file2.getFileItem()).deleted);

		resolver.setEmpty(true);
		request = resolver.resolveMultipart(originalRequest);
		binder.setBindEmptyMultipartFiles(false);
		String firstBound = mtb2.getField2();
		binder.bind(request);
		assertFalse(mtb2.getField2().isEmpty());
		assertEquals(firstBound, mtb2.getField2());

		request = resolver.resolveMultipart(originalRequest);
		binder.setBindEmptyMultipartFiles(true);
		binder.bind(request);
		assertTrue(mtb2.getField2().isEmpty());
	}
