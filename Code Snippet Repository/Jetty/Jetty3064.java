    protected void generateAcceptableResponse(Request baseRequest, HttpServletRequest request, HttpServletResponse response, int code, String message, String mimeType)
        throws IOException
    {
        switch(mimeType)
        {
            case "text/html":
            case "text/*":
            case "*/*":
            {
                baseRequest.setHandled(true);
                Writer writer = getAcceptableWriter(baseRequest,request,response);
                if (writer!=null)
                {
                    response.setContentType(MimeTypes.Type.TEXT_HTML.asString());
                    handleErrorPage(request, writer, code, message);
                }
            }
        }        
    }
