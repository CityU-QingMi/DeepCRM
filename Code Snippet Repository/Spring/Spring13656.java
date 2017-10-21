	@Before
	public void setUp() throws Exception {
		this.request = new MockHttpServletRequest();
		this.request.setContextPath("/context");
		this.request.setCharacterEncoding(WebUtils.DEFAULT_CHARACTER_ENCODING);
		this.request.setAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE, new FlashMap());
		this.request.setAttribute(DispatcherServlet.FLASH_MAP_MANAGER_ATTRIBUTE, new SessionFlashMapManager());
		this.response = new MockHttpServletResponse();

	}
