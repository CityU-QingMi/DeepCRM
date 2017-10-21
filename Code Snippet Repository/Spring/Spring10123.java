	@Test
	public void writeMultipartOrder() throws Exception {
		MyBean myBean = new MyBean();
		myBean.setString("foo");

		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		parts.add("part1", myBean);

		HttpHeaders entityHeaders = new HttpHeaders();
		entityHeaders.setContentType(MediaType.TEXT_XML);
		HttpEntity<MyBean> entity = new HttpEntity<>(myBean, entityHeaders);
		parts.add("part2", entity);

		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		this.converter.setMultipartCharset(StandardCharsets.UTF_8);
		this.converter.write(parts, new MediaType("multipart", "form-data", StandardCharsets.UTF_8), outputMessage);

		final MediaType contentType = outputMessage.getHeaders().getContentType();
		assertNotNull("No boundary found", contentType.getParameter("boundary"));

		// see if Commons FileUpload can read what we wrote
		FileItemFactory fileItemFactory = new DiskFileItemFactory();
		FileUpload fileUpload = new FileUpload(fileItemFactory);
		RequestContext requestContext = new MockHttpOutputMessageRequestContext(outputMessage);
		List<FileItem> items = fileUpload.parseRequest(requestContext);
		assertEquals(2, items.size());

		FileItem item = items.get(0);
		assertTrue(item.isFormField());
		assertEquals("part1", item.getFieldName());
		assertEquals("{\"string\":\"foo\"}", item.getString());

		item = items.get(1);
		assertTrue(item.isFormField());
		assertEquals("part2", item.getFieldName());

		// With developer builds we get: <MyBean><string>foo</string></MyBean>
		// But on CI server we get: <MyBean xmlns=""><string>foo</string></MyBean>
		// So... we make a compromise:
		assertThat(item.getString(),
				allOf(startsWith("<MyBean"), endsWith("><string>foo</string></MyBean>")));
	}
