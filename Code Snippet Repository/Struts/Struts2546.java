    private Servlet createServlet(Class clazz) throws IllegalAccessException, InstantiationException, ServletException {
        JSPServletConfig config = new JSPServletConfig(ServletActionContext.getServletContext());

        Servlet servlet = (Servlet) clazz.newInstance();
        servlet.init(config);

/**/
/**/
/**/
/**/
/**/
/**/

        return servlet;
    }
