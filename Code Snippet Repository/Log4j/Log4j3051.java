    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext asyncContext = req.startAsync();
        asyncContext.start(WebLoggerContextUtils.wrapExecutionContext(this.getServletContext(), new Runnable() {
            @Override
            public void run() {
                final Logger logger = LogManager.getLogger(TestAsyncServlet.class);
                logger.info("Hello, servlet!");
            }
        }));
    }
