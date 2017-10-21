	@Test
	public void withServletContextAndFilter() throws Exception {
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(new MockServletContext());
		wac.registerSingleton("filterMultipartResolver", MockCommonsMultipartResolver.class, new MutablePropertyValues());
		wac.getServletContext().setAttribute(WebUtils.TEMP_DIR_CONTEXT_ATTRIBUTE, new File("mytemp"));
		wac.refresh();
		wac.getServletContext().setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
		CommonsMultipartResolver resolver = new CommonsMultipartResolver(wac.getServletContext());
		assertTrue(resolver.getFileItemFactory().getRepository().getAbsolutePath().endsWith("mytemp"));

		MockFilterConfig filterConfig = new MockFilterConfig(wac.getServletContext(), "filter");
		filterConfig.addInitParameter("class", "notWritable");
		filterConfig.addInitParameter("unknownParam", "someValue");
		final MultipartFilter filter = new MultipartFilter();
		filter.init(filterConfig);

		final List<MultipartFile> files = new ArrayList<>();
		final FilterChain filterChain = new FilterChain() {
			@Override
			public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) {
				MultipartHttpServletRequest request = (MultipartHttpServletRequest) servletRequest;
				files.addAll(request.getFileMap().values());
			}
		};

		FilterChain filterChain2 = new PassThroughFilterChain(filter, filterChain);

		MockHttpServletRequest originalRequest = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		originalRequest.setMethod("POST");
		originalRequest.setContentType("multipart/form-data");
		originalRequest.addHeader("Content-type", "multipart/form-data");
		filter.doFilter(originalRequest, response, filterChain2);

		CommonsMultipartFile file1 = (CommonsMultipartFile) files.get(0);
		CommonsMultipartFile file2 = (CommonsMultipartFile) files.get(1);
		assertTrue(((MockFileItem) file1.getFileItem()).deleted);
		assertTrue(((MockFileItem) file2.getFileItem()).deleted);
	}
