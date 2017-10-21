    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        try
        {
            InitialContext ic = new InitialContext();
            woggle = (Integer)ic.lookup("java:comp/env/woggle");
            envEntryGlobalScopeResult = "EnvEntry defined in context xml lookup result (java:comp/env/woggle): "+(woggle==4000?"<span class=\"pass\">PASS":"<span class=\"fail\">FAIL(expected 4000, got "+woggle+")")+"</span>";
            gargle = (Double)ic.lookup("java:comp/env/gargle");
            envEntryWebAppScopeResult = "EnvEntry defined in jetty-env.xml lookup result (java:comp/env/gargle): "+(gargle==100.0?"<span class=\"pass\">PASS":"<span class=\"fail\">FAIL(expected 100, got "+gargle+")")+"</span>";
            UserTransaction utx = (UserTransaction)ic.lookup("java:comp/UserTransaction");
            userTransactionResult = "UserTransaction lookup result (java:comp/UserTransaction): "+(utx!=null?"<span class=\"pass\">PASS":"<span class=\"fail\">FAIL")+"</span>";
            myMailSession = (Session)ic.lookup("java:comp/env/mail/Session");
            mailSessionResult = "Mail Session lookup result (java:comp/env/mail/Session): "+(myMailSession!=null?"<span class=\"pass\">PASS": "<span class=\"fail\">FAIL")+"</span>";
        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }
    }
