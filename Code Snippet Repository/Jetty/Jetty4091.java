        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            RequestDispatcher dispatcher = null;

            if(request.getParameter("do").equals("include"))
                dispatcher = getServletContext().getRequestDispatcher("/IncludeServlet/includepath?do=assertforwardinclude");
            else if(request.getParameter("do").equals("assertincludeforward"))
                dispatcher = getServletContext().getRequestDispatcher("/AssertIncludeForwardServlet/assertpath?do=end");
            else if(request.getParameter("do").equals("assertforward"))
                dispatcher = getServletContext().getRequestDispatcher("/AssertForwardServlet?do=end&do=the");
            else if(request.getParameter("do").equals("ctx.echo"))
                dispatcher = getServletContext().getRequestDispatcher(request.getParameter("uri"));
            else if(request.getParameter("do").equals("req.echo"))
                dispatcher = request.getRequestDispatcher(request.getParameter("uri"));
            dispatcher.forward(request, response);
        }
