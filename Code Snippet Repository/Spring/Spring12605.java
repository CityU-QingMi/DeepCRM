	@Before
	public void setup() {
		this.request = new MockHttpServletRequest();
		this.response= new MockHttpServletResponse() ;

		this.handler = new Object();
		this.chain = new HandlerExecutionChain(this.handler);

		this.interceptor1 = mock(AsyncHandlerInterceptor.class);
		this.interceptor2 = mock(AsyncHandlerInterceptor.class);
		this.interceptor3 = mock(AsyncHandlerInterceptor.class);

		this.chain.addInterceptor(this.interceptor1);
		this.chain.addInterceptor(this.interceptor2);
		assertEquals(2, this.chain.getInterceptors().length);
		this.chain.addInterceptor(this.interceptor3);
		assertEquals(3, this.chain.getInterceptors().length);
	}
