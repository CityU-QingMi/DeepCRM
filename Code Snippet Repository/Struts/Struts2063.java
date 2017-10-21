    protected void setUp() throws Exception {
        super.setUp();
        stack = ActionContext.getContext().getValueStack();

        jspWriter = new MockJspWriter();

        StrutsMockHttpServletRequest request = new StrutsMockHttpServletRequest();

        StrutsMockServletContext servletContext = new StrutsMockServletContext();
        servletContext.setServletInfo("not-weblogic");

        pageContext = new MockPageContext();
        pageContext.setJspWriter(jspWriter);
        pageContext.setRequest(request);
        pageContext.setServletContext(servletContext);

        request.setAttribute(ServletActionContext.STRUTS_VALUESTACK_KEY, stack);
    }
