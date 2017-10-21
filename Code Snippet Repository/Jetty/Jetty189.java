    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/plain");

        String timeType = req.getParameter("type");
        TimeFormatter time = LocaleTimeFormatter.INSTANCE;

        Instance<TimeFormatter> inst = formatters.select(new NamedLiteral(timeType));
        if (!inst.isAmbiguous() && !inst.isUnsatisfied())
        {
            time = inst.get();
        }

        resp.getWriter().println(time.format(Calendar.getInstance()));
    }
