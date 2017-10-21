    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/plain");

        PrintWriter out = resp.getWriter();
        if (context == null)
        {
            out.println("context = null");
            return;
        }
        out.printf("context = %s%n",context);
        out.printf("context.contextPath = %s%n",context.getContextPath());
        out.printf("context.effective-version = %d.%d%n",context.getEffectiveMajorVersion(),context.getEffectiveMinorVersion());
    }
