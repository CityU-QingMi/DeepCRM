	private void _initialize(Servlet servlet, ServletRequest request,
			ServletResponse response, String errorPageURL,
			boolean needsSession, int bufferSize, boolean autoFlush)
			throws IOException {

		// initialize state
		this.servlet = servlet;
		this.config = servlet.getServletConfig();
		this.context = config.getServletContext();
		this.errorPageURL = errorPageURL;
		this.request = request;
		this.response = response;
		
		// initialize application context
		this.applicationContext = JspApplicationContextImpl.getInstance(context);

		// Setup session (if required)
		if (request instanceof HttpServletRequest && needsSession)
			this.session = ((HttpServletRequest) request).getSession();
		if (needsSession && session == null)
			throw new IllegalStateException(
					"Page needs a session and none is available");

		// initialize the initial out ...
		depth = -1;
		if (this.baseOut == null) {
			this.baseOut = new JspWriterImpl(response, bufferSize, autoFlush);
		} else {
			this.baseOut.init(response, bufferSize, autoFlush);
		}
		this.out = baseOut;

		// register names/values as per spec
		setAttribute(OUT, this.out);
		setAttribute(REQUEST, request);
		setAttribute(RESPONSE, response);

		if (session != null)
			setAttribute(SESSION, session);

		setAttribute(PAGE, servlet);
		setAttribute(CONFIG, config);
		setAttribute(PAGECONTEXT, this);
		setAttribute(APPLICATION, context);

		isIncluded = request.getAttribute("javax.servlet.include.servlet_path") != null;
	}
