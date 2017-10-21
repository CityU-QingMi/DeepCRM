	@Before
	public void setup() throws Exception {
		this.resolver = new MatrixVariableMapMethodArgumentResolver();
		this.mavContainer = new ModelAndViewContainer();
		this.request = new MockHttpServletRequest();
		this.webRequest = new ServletWebRequest(request, new MockHttpServletResponse());

		Map<String, MultiValueMap<String, String>> params = new LinkedHashMap<>();
		this.request.setAttribute(HandlerMapping.MATRIX_VARIABLES_ATTRIBUTE, params);

		Method method = getClass().getMethod("handle", String.class,
				Map.class, MultiValueMap.class, MultiValueMap.class, Map.class);

		this.paramString = new SynthesizingMethodParameter(method, 0);
		this.paramMap = new SynthesizingMethodParameter(method, 1);
		this.paramMultivalueMap = new SynthesizingMethodParameter(method, 2);
		this.paramMapForPathVar = new SynthesizingMethodParameter(method, 3);
		this.paramMapWithName = new SynthesizingMethodParameter(method, 4);
	}
