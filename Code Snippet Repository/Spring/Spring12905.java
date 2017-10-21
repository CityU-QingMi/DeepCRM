	@Before
	public void setup() throws Exception {
		this.resolver = new MatrixVariableMethodArgumentResolver();
		this.mavContainer = new ModelAndViewContainer();
		this.request = new MockHttpServletRequest();
		this.webRequest = new ServletWebRequest(request, new MockHttpServletResponse());

		Map<String, MultiValueMap<String, String>> params = new LinkedHashMap<>();
		this.request.setAttribute(HandlerMapping.MATRIX_VARIABLES_ATTRIBUTE, params);

		Method method = getClass().getMethod("handle", String.class, List.class, int.class);
		this.paramString = new MethodParameter(method, 0);
		this.paramColors = new MethodParameter(method, 1);
		this.paramColors.initParameterNameDiscovery(new LocalVariableTableParameterNameDiscoverer());
		this.paramYear = new MethodParameter(method, 2);
	}
