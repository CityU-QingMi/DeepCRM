	@Test
	public void addContentDispositionHeader() throws Exception {
		ContentNegotiationManagerFactoryBean factory = new ContentNegotiationManagerFactoryBean();
		factory.addMediaType("pdf", new MediaType("application", "pdf"));
		factory.afterPropertiesSet();

		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(
				Collections.singletonList(new StringHttpMessageConverter()),
				factory.getObject());

		assertContentDisposition(processor, false, "/hello.json", "whitelisted extension");
		assertContentDisposition(processor, false, "/hello.pdf", "registered extension");
		assertContentDisposition(processor, true, "/hello.dataless", "uknown extension");

		// path parameters
		assertContentDisposition(processor, false, "/hello.json;a=b", "path param shouldn't cause issue");
		assertContentDisposition(processor, true, "/hello.json;a=b;setup.dataless", "uknown ext in path params");
		assertContentDisposition(processor, true, "/hello.dataless;a=b;setup.json", "uknown ext in filename");
		assertContentDisposition(processor, false, "/hello.json;a=b;setup.json", "whitelisted extensions");

		// encoded dot
		assertContentDisposition(processor, true, "/hello%2Edataless;a=b;setup.json", "encoded dot in filename");
		assertContentDisposition(processor, true, "/hello.json;a=b;setup%2Edataless", "encoded dot in path params");
		assertContentDisposition(processor, true, "/hello.dataless%3Bsetup.bat", "encoded dot in path params");

		this.servletRequest.setAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE, "/hello.bat");
		assertContentDisposition(processor, true, "/bonjour", "forwarded URL");
		this.servletRequest.removeAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);
	}
