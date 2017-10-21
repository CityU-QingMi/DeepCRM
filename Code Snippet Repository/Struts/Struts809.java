    protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
        // verify charset
        Charset charset = readCharset();

        HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(HTTP_RESPONSE);

        applyCharset(charset, response);
        applyAdditionalHeaders(response);
        String location = adjustLocation(finalLocation);

        try (PrintWriter writer = response.getWriter();
                InputStream resourceAsStream = readStream(invocation, location);
                InputStreamReader reader = new InputStreamReader(resourceAsStream, charset == null ? Charset.defaultCharset() : charset)) {
            logWrongStream(finalLocation, resourceAsStream);
            sendStream(writer, reader);
        }
    }
