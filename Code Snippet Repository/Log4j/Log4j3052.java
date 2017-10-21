    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext asyncContext = req.startAsync();
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                final Log4jWebSupport webSupport =
                    WebLoggerContextUtils.getWebLifeCycle(TestAsyncServlet.this.getServletContext());
                webSupport.setLoggerContext();
                // do stuff
                webSupport.clearLoggerContext();
            }
        });
    }
