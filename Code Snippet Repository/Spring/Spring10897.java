	private void doTestWithApplicationContext(boolean lazy) throws Exception {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(new MockServletContext());
		wac.getServletContext().setAttribute(WebUtils.TEMP_DIR_CONTEXT_ATTRIBUTE, new File("mytemp"));
		wac.refresh();
		MockCommonsMultipartResolver resolver = new MockCommonsMultipartResolver();
		resolver.setMaxUploadSize(1000);
		resolver.setMaxInMemorySize(100);
		resolver.setDefaultEncoding("enc");
		if (lazy) {
			resolver.setResolveLazily(false);
		}
		resolver.setServletContext(wac.getServletContext());
		assertEquals(1000, resolver.getFileUpload().getSizeMax());
		assertEquals(100, resolver.getFileItemFactory().getSizeThreshold());
		assertEquals("enc", resolver.getFileUpload().getHeaderEncoding());
		assertTrue(resolver.getFileItemFactory().getRepository().getAbsolutePath().endsWith("mytemp"));

		MockHttpServletRequest originalRequest = new MockHttpServletRequest();
		originalRequest.setMethod("POST");
		originalRequest.setContentType("multipart/form-data");
		originalRequest.addHeader("Content-type", "multipart/form-data");
		originalRequest.addParameter("getField", "getValue");
		assertTrue(resolver.isMultipart(originalRequest));
		MultipartHttpServletRequest request = resolver.resolveMultipart(originalRequest);

		doTestParameters(request);

		doTestFiles(request);

		doTestBinding(resolver, originalRequest, request);

		wac.close();
	}
