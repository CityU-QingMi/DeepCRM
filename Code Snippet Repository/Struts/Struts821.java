    public void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletContext servletContext = ServletActionContext.getServletContext();
        Servlet servlet = JspSupportServlet.jspSupportServlet;

        velocityManager.init(servletContext);

        boolean usedJspFactory = false;
        PageContext pageContext = (PageContext) ActionContext.getContext().get(ServletActionContext.PAGE_CONTEXT);

        if (pageContext == null && servlet != null) {
            pageContext = jspFactory.getPageContext(servlet, request, response, null, true, 8192, true);
            ActionContext.getContext().put(ServletActionContext.PAGE_CONTEXT, pageContext);
            usedJspFactory = true;
        }

        try {
            String encoding = getEncoding(finalLocation);
            String contentType = getContentType(finalLocation);

            if (encoding != null) {
                contentType = contentType + ";charset=" + encoding;
            }

            Template t = getTemplate(stack, velocityManager.getVelocityEngine(), invocation, finalLocation, encoding);

            Context context = createContext(velocityManager, stack, request, response, finalLocation);
            Writer writer = new OutputStreamWriter(response.getOutputStream(), encoding);


            response.setContentType(contentType);

            t.merge(context, writer);

            // always flush the writer (we used to only flush it if this was a jspWriter, but someone asked
            // to do it all the time (WW-829). Since Velocity support is being deprecated, we'll oblige :)
            writer.flush();
        } catch (Exception e) {
            LOG.error("Unable to render velocity template: '{}'", finalLocation, e);
            throw e;
        } finally {
            if (usedJspFactory) {
                jspFactory.releasePageContext(pageContext);
            }
        }
    }
