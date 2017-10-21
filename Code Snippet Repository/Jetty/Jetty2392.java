    @Test
    public void testDownstreamTransformationThrowsAtOnContent() throws Exception
    {
        testDownstreamTransformationThrows(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                // To trigger the test failure we need that onContent()
                // is called twice, so the second time the test throws.
                ServletOutputStream output = response.getOutputStream();
                output.write(new byte[512]);
                output.flush();
                output.write(new byte[512]);
                output.flush();
            }
        });
    }
