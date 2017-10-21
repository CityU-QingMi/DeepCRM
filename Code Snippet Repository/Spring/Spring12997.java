	@Before
	@SuppressWarnings("")
	public void setup() throws Exception {
		stringMessageConverter = mock(HttpMessageConverter.class);
		given(stringMessageConverter.getSupportedMediaTypes()).willReturn(Collections.singletonList(MediaType.TEXT_PLAIN));
		resourceMessageConverter = mock(HttpMessageConverter.class);
		given(resourceMessageConverter.getSupportedMediaTypes()).willReturn(Collections.singletonList(MediaType.ALL));
		resourceRegionMessageConverter = mock(HttpMessageConverter.class);
		given(resourceRegionMessageConverter.getSupportedMediaTypes()).willReturn(Collections.singletonList(MediaType.ALL));

		processor = new RequestResponseBodyMethodProcessor(
				Arrays.asList(stringMessageConverter, resourceMessageConverter, resourceRegionMessageConverter));

		mavContainer = new ModelAndViewContainer();
		servletRequest = new MockHttpServletRequest();
		servletRequest.setMethod("POST");
		servletResponse = new MockHttpServletResponse();
		webRequest = new ServletWebRequest(servletRequest, servletResponse);

		Method methodHandle1 = getClass().getMethod("handle1", String.class, Integer.TYPE);
		paramRequestBodyString = new MethodParameter(methodHandle1, 0);
		paramInt = new MethodParameter(methodHandle1, 1);
		paramValidBean = new MethodParameter(getClass().getMethod("handle2", SimpleBean.class), 0);
		paramStringNotRequired = new MethodParameter(getClass().getMethod("handle3", String.class), 0);
		paramOptionalString = new MethodParameter(getClass().getMethod("handle4", Optional.class), 0);
		returnTypeString = new MethodParameter(methodHandle1, -1);
		returnTypeInt = new MethodParameter(getClass().getMethod("handle5"), -1);
		returnTypeStringProduces = new MethodParameter(getClass().getMethod("handle6"), -1);
		returnTypeResource = new MethodParameter(getClass().getMethod("handle7"), -1);
	}
