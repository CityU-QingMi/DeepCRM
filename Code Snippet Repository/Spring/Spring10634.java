	@Before
	public void setup() {
		this.servletRequest = new MockHttpServletRequest();
		this.asyncManager = WebAsyncUtils.getAsyncManager(servletRequest);
		this.asyncManager.setTaskExecutor(new SyncTaskExecutor());
		this.asyncWebRequest = mock(AsyncWebRequest.class);
		this.asyncManager.setAsyncWebRequest(this.asyncWebRequest);
		verify(this.asyncWebRequest).addCompletionHandler((Runnable) notNull());
		reset(this.asyncWebRequest);
	}
