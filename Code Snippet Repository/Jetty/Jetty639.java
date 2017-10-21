    @Test
    public void testServerClosesConnectionAfterResponseWithQueuedRequestWithMaxConnectionsWithoutConnectionCloseHeader() throws Exception
    {
        testServerClosesConnectionAfterResponseWithQueuedRequestWithMaxConnections(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.setStatus(HttpStatus.OK_200);
                response.setContentLength(0);
                response.flushBuffer();
                baseRequest.getHttpChannel().getEndPoint().shutdownOutput();
            }
        });
    }
