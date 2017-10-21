    protected void render(Content content, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, SiteMeshWebAppContext webAppContext) throws IOException, ServletException {

        // see if the URI path (webapp) is set
        if (oldDecorator.getURIPath() != null) {
            // in a security conscious environment, the servlet container
            // may return null for a given URL
            if (servletContext.getContext(oldDecorator.getURIPath()) != null) {
                servletContext = servletContext.getContext(oldDecorator.getURIPath());
            }
        }

        ActionContext ctx = ServletActionContext.getActionContext(request);
        if (ctx == null) {
            // ok, one isn't associated with the request, so let's create one using the current Dispatcher
            ValueStack vs = Dispatcher.getInstance().getContainer().getInstance(ValueStackFactory.class).createValueStack();
            vs.getContext().putAll(Dispatcher.getInstance().createContextMap(request, response, null));
            ctx = new ActionContext(vs.getContext());
            if (ctx.getActionInvocation() == null) {
                // put in a dummy ActionSupport so basic functionality still works
                ActionSupport action = new ActionSupport();
                vs.push(action);
                ctx.setActionInvocation(new DummyActionInvocation(action));
            }
        }

        // delegate to the actual page decorator
        render(content, request, response, servletContext, ctx);

    }
