	@Before
	@SuppressWarnings("")
	public void setup() throws Exception {
		stringHttpMessageConverter = mock(HttpMessageConverter.class);
		given(stringHttpMessageConverter.getSupportedMediaTypes()).willReturn(Collections.singletonList(MediaType.TEXT_PLAIN));
		resourceMessageConverter = mock(HttpMessageConverter.class);
		given(resourceMessageConverter.getSupportedMediaTypes()).willReturn(Collections.singletonList(MediaType.ALL));
		resourceRegionMessageConverter = mock(HttpMessageConverter.class);
		given(resourceRegionMessageConverter.getSupportedMediaTypes()).willReturn(Collections.singletonList(MediaType.ALL));

		processor = new HttpEntityMethodProcessor(
				Arrays.asList(stringHttpMessageConverter, resourceMessageConverter, resourceRegionMessageConverter));

		Method handle1 = getClass().getMethod("handle1", HttpEntity.class, ResponseEntity.class,
				Integer.TYPE, RequestEntity.class);

		paramHttpEntity = new MethodParameter(handle1, 0);
		paramRequestEntity = new MethodParameter(handle1, 3);
		paramResponseEntity = new MethodParameter(handle1, 1);
		paramInt = new MethodParameter(handle1, 2);
		returnTypeResponseEntity = new MethodParameter(handle1, -1);
		returnTypeResponseEntityProduces = new MethodParameter(getClass().getMethod("handle4"), -1);
		returnTypeHttpEntity = new MethodParameter(getClass().getMethod("handle2", HttpEntity.class), -1);
		returnTypeHttpEntitySubclass = new MethodParameter(getClass().getMethod("handle2x", HttpEntity.class), -1);
		returnTypeInt = new MethodParameter(getClass().getMethod("handle3"), -1);
		returnTypeResponseEntityResource = new MethodParameter(getClass().getMethod("handle5"), -1);

		mavContainer = new ModelAndViewContainer();
		servletRequest = new MockHttpServletRequest("GET", "/foo");
		servletResponse = new MockHttpServletResponse();
		webRequest = new ServletWebRequest(servletRequest, servletResponse);
	}
