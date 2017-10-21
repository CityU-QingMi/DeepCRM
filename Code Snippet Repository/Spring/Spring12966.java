	@Before
	@SuppressWarnings("")
	public void setup() throws Exception {
		messageConverter = mock(HttpMessageConverter.class);
		given(messageConverter.getSupportedMediaTypes()).willReturn(Collections.singletonList(MediaType.TEXT_PLAIN));

		resolver = new RequestPartMethodArgumentResolver(Collections.<HttpMessageConverter<?>>singletonList(messageConverter));
		reset(messageConverter);

		byte[] content = "doesn't matter as long as not empty".getBytes(StandardCharsets.UTF_8);
		multipartFile1 = new MockMultipartFile("requestPart", "", "text/plain", content);
		multipartFile2 = new MockMultipartFile("requestPart", "", "text/plain", content);
		multipartRequest = new MockMultipartHttpServletRequest();
		multipartRequest.addFile(multipartFile1);
		multipartRequest.addFile(multipartFile2);
		multipartRequest.addFile(new MockMultipartFile("otherPart", "", "text/plain", content));
		webRequest = new ServletWebRequest(multipartRequest, new MockHttpServletResponse());

		Method method = ReflectionUtils.findMethod(getClass(), "handle", (Class<?>[]) null);
		paramRequestPart = new SynthesizingMethodParameter(method, 0);
		paramRequestPart.initParameterNameDiscovery(new LocalVariableTableParameterNameDiscoverer());
		paramNamedRequestPart = new SynthesizingMethodParameter(method, 1);
		paramValidRequestPart = new SynthesizingMethodParameter(method, 2);
		paramMultipartFile = new SynthesizingMethodParameter(method, 3);
		paramMultipartFileList = new SynthesizingMethodParameter(method, 4);
		paramMultipartFileArray = new SynthesizingMethodParameter(method, 5);
		paramInt = new SynthesizingMethodParameter(method, 6);
		paramMultipartFileNotAnnot = new SynthesizingMethodParameter(method, 7);
		paramMultipartFileNotAnnot.initParameterNameDiscovery(new LocalVariableTableParameterNameDiscoverer());
		paramPart = new SynthesizingMethodParameter(method, 8);
		paramPart.initParameterNameDiscovery(new LocalVariableTableParameterNameDiscoverer());
		paramPartList = new SynthesizingMethodParameter(method, 9);
		paramPartArray = new SynthesizingMethodParameter(method, 10);
		paramRequestParamAnnot = new SynthesizingMethodParameter(method, 11);
		optionalMultipartFile = new SynthesizingMethodParameter(method, 12);
		optionalMultipartFile.initParameterNameDiscovery(new LocalVariableTableParameterNameDiscoverer());
		optionalMultipartFileList = new SynthesizingMethodParameter(method, 13);
		optionalMultipartFileList.initParameterNameDiscovery(new LocalVariableTableParameterNameDiscoverer());
		optionalPart = new SynthesizingMethodParameter(method, 14);
		optionalPart.initParameterNameDiscovery(new LocalVariableTableParameterNameDiscoverer());
		optionalPartList = new SynthesizingMethodParameter(method, 15);
		optionalPartList.initParameterNameDiscovery(new LocalVariableTableParameterNameDiscoverer());
		optionalRequestPart = new SynthesizingMethodParameter(method, 16);
	}
