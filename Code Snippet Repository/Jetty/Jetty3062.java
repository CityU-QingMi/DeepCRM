    protected void generateAcceptableResponse(Request baseRequest, HttpServletRequest request, HttpServletResponse response, int code, String message)
        throws IOException
    {
        List<String> acceptable=baseRequest.getHttpFields().getQualityCSV(HttpHeader.ACCEPT);
        
        if (acceptable.isEmpty() && !baseRequest.getHttpFields().contains(HttpHeader.ACCEPT))
        {
            generateAcceptableResponse(baseRequest,request,response,code,message,MimeTypes.Type.TEXT_HTML.asString());
        }
        else
        {
            for (String mimeType:acceptable)
            {
                generateAcceptableResponse(baseRequest,request,response,code,message,mimeType);
                if (baseRequest.isHandled())
                    break;
            }
        }
        baseRequest.setHandled(true);
        baseRequest.getResponse().closeOutput();
    }
