    @Test
    public void testDownstreamTransformationThrowsAtOnSuccess() throws Exception
    {
        testDownstreamTransformationThrows(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                // To trigger the test failure we need that onContent()
                // is called only once, so the the test throws from onSuccess().
                ServletOutputStream output = response.getOutputStream();
                output.write(new byte[512]);
                output.flush();
            }
        });
    }
