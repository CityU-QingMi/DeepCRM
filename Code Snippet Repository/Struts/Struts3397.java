    protected String executeAction(String httpMethod, String uri) throws ServletException, UnsupportedEncodingException {
        request.setRequestURI(uri);
        request.setMethod(httpMethod);

        ActionMapping mapping = getActionMapping(request);

        assertNotNull(mapping);
        Dispatcher.getInstance().serviceAction(request, response, mapping);

        if (response.getStatus() != HttpServletResponse.SC_OK)
            throw new ServletException("Error code [" + response.getStatus() + "], Error: ["
                    + response.getErrorMessage() + "]");

        return response.getContentAsString();
    }
