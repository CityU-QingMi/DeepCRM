    protected void render(Content content, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ActionContext ctx) throws ServletException, IOException {
        String timerKey = "FreemarkerPageFilter_applyDecorator: ";
        if (velocityManager == null) {
            throw new ServletException("Missing freemarker dependency");
        }

        try {

            // init (if needed)
            velocityManager.init(servletContext);

            // get encoding
            String encoding = getEncoding();

            HTMLPage htmlPage = new Content2HTMLPage(content, request);

            // get the template and context
            org.apache.velocity.Template template = velocityManager.getVelocityEngine().getTemplate(oldDecorator.getPage(), encoding);
            Context context = velocityManager.createContext(ctx.getValueStack(), request, response);

            // put the page in the context
            context.put("page", htmlPage);
            context.put("head", htmlPage.getHead());
            context.put("title", htmlPage.getTitle());
            context.put("body", htmlPage.getBody());

            // finally, render it
            PrintWriter writer = response.getWriter();
            template.merge(context, writer);
            writer.flush();
        } catch (Exception e) {
            String msg = "Error applying decorator to request: " + request.getRequestURL() + "?" + request.getQueryString() + " with message:" + e.getMessage();
            LOG.error(msg, e);
            throw new ServletException(msg, e);
        }
    }
