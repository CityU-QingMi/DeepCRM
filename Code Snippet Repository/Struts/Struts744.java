    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            prepare.createActionContext(request, response);
            prepare.assignDispatcherToThread();
            prepare.setEncodingAndLocale(request, response);
            request = prepare.wrapRequest(request);
            ActionMapping mapping = prepare.findActionMapping(request, response);
            if (mapping == null) {
                boolean handled = execute.executeStaticResourceRequest(request, response);
                if (!handled)
                    throw new ServletException("Resource loading not supported, use the StrutsPrepareAndExecuteFilter instead.");
            } else {
                execute.executeAction(request, response, mapping);
            }
        } finally {
            prepare.cleanupRequest(request);
        }
    }
