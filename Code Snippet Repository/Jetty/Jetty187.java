    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        
        Dumper dumper = defaultDumper;
        
        String dumperId = req.getParameter("dumperId");
        
        if (dumperId != null)
        {
            Instance<Dumper> inst = dumpers.select(new NamedLiteral(dumperId));
            if (!inst.isAmbiguous() && !inst.isUnsatisfied())
            {
                dumper = inst.get();
            }
        }
        
        dumper.dump(out);
    }
