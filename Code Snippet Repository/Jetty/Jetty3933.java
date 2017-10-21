    protected void initJspServlet () throws Exception
    {
        ContextHandler ch = ContextHandler.getContextHandler(getServletHandler().getServletContext());

        /* Set the webapp's classpath for Jasper */
        ch.setAttribute("org.apache.catalina.jsp_classpath", ch.getClassPath());

        /* Set up other classpath attribute */
        if ("?".equals(getInitParameter("classpath")))
        {
            String classpath = ch.getClassPath();
            if (LOG.isDebugEnabled())
                LOG.debug("classpath=" + classpath);
            if (classpath != null)
                setInitParameter("classpath", classpath);
        }

        /* ensure scratch dir */
        File scratch = null;
        if (getInitParameter("scratchdir") == null)
        {
            File tmp = (File)getServletHandler().getServletContext().getAttribute(ServletContext.TEMPDIR);
            scratch = new File(tmp, "jsp");
            setInitParameter("scratchdir", scratch.getAbsolutePath());
        }

        scratch = new File (getInitParameter("scratchdir"));
        if (!scratch.exists()) scratch.mkdir();
    }
