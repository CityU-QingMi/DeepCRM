    @Before
    public void before() throws Exception
    {
        server = new Server();
        connector = new LocalConnector(server);
        server.addConnector(connector);
        server.addBean(new ErrorHandler()
        {
            @Override
            protected void generateAcceptableResponse(
                Request baseRequest, 
                HttpServletRequest request, 
                HttpServletResponse response, 
                int code, 
                String message,
                String mimeType) throws IOException
            {
                switch(mimeType)
                {
                    case "text/json":
                    case "application/json":
                    {
                        baseRequest.setHandled(true);
                        response.setContentType(mimeType);
                        response.getWriter()
                         .append("{")
                         .append("code: \"").append(Integer.toString(code)).append("\",")
                         .append("message: \"").append(message).append('"')
                         .append("}");
                        break;
                    }
                    default:
                        super.generateAcceptableResponse(baseRequest,request,response,code,message,mimeType);
                }
            }
            
        });
        server.start();
    }
