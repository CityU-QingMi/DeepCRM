    protected void writeErrorPageBody(HttpServletRequest request, Writer writer, int code, String message, boolean showStacks)
        throws IOException
    {
        String uri= request.getRequestURI();

        writeErrorPageMessage(request,writer,code,message,uri);
        if (showStacks)
            writeErrorPageStacks(request,writer);

        Request.getBaseRequest(request).getHttpChannel().getHttpConfiguration()
            .writePoweredBy(writer,"<hr>","<hr/>\n");
    }
