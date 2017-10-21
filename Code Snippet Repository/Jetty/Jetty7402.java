    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException
    {
        String path = req.getServletPath();
        URL url =getServletContext().getResource(path);
        if (url==null)
        {
            response.sendError(404);
            return;
        }
            
        try
        {
            File file=new File(url.toURI());
            if (file.exists())
            {
                response.sendError(200,"fake JSP response");
                return;
            }   
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        response.sendError(404);
    }
