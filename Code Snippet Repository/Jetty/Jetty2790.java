    @SuppressWarnings("")
    private void commitResponse(ServletResponse response, Request baseRequest) throws IOException, ServletException
    {
        if (baseRequest.getResponse().isWriting())
        {
            try
            {
                // Try closing Writer first (based on knowledge in Response obj)
                response.getWriter().close();
            }
            catch (IllegalStateException e1)
            {
                try
                {
                    // Try closing OutputStream as alternate route
                    // This path is possible due to badly behaving Response wrappers
                    response.getOutputStream().close();
                }
                catch(IllegalStateException e2)
                {
                    ServletException servletException = new ServletException("Unable to commit the response", e2);
                    servletException.addSuppressed(e1);
                    throw servletException;
                }
            }
        }
        else
        {
            try
            {
                // Try closing OutputStream first (based on knowledge in Response obj)
                response.getOutputStream().close();
            }
            catch (IllegalStateException e1)
            {
                try
                {
                    // Try closing Writer as alternate route
                    // This path is possible due to badly behaving Response wrappers
                    response.getWriter().close();
                }
                catch(IllegalStateException e2)
                {
                    ServletException servletException = new ServletException("Unable to commit the response", e2);
                    servletException.addSuppressed(e1);
                    throw servletException;
                }
            }
        }
    }
