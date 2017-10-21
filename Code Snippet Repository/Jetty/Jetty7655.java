    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        try
        {
            response.setContentType("text/html");
            ServletOutputStream out = response.getOutputStream();
            out.println("<html>");
            out.println("<HEAD><link rel=\"stylesheet\" type=\"text/css\"  href=\"stylesheet.css\"/></HEAD>");
            out.println("<body>");
            out.println("<h1>Results</h1>");
            out.println("<p>");

            Collection<Part> parts = request.getParts();
            out.println("<b>Parts:</b>&nbsp;"+parts.size());
            for (Part p: parts)
            {
                out.println("<h3>"+p.getName()+"</h3>");
                out.println("<b>Size:</b>&nbsp;"+p.getSize());
                if (p.getContentType() == null || p.getContentType().startsWith("text/plain"))
                {
                    out.println("<p>");
                    IO.copy(p.getInputStream(),out);
                    out.println("</p>");
                }
            } 
            out.println("</body>");            
            out.println("</html>");
            out.flush();
        }
        catch (ServletException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }
    }
