    protected void render(Content content, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ActionContext ctx) throws ServletException, IOException {
        String timerKey = "FreemarkerPageFilter_applyDecorator: ";
        if (freemarkerManager == null) {
            throw new ServletException("Missing freemarker dependency");
        }

        try {
            UtilTimerStack.push(timerKey);

            // get the configuration and template
            Configuration config = freemarkerManager.getConfiguration(servletContext);
            Template template = config.getTemplate(oldDecorator.getPage(), getLocale(ctx.getActionInvocation(), config)); // WW-1181

            // get the main hash
            ScopesHashModel model = (ScopesHashModel) request.getAttribute(freemarkerManager.ATTR_TEMPLATE_MODEL);
            if (model == null) {
                model = freemarkerManager.buildTemplateModel(ctx.getValueStack(), ctx.getActionInvocation().getAction(), servletContext, request, response, config.getObjectWrapper());
            }

            // populate the hash with the page
            HTMLPage htmlPage = new Content2HTMLPage(content, request);
            model.put("page", htmlPage);
            model.put("head", htmlPage.getHead());
            model.put("title", htmlPage.getTitle());
            model.put("body", htmlPage.getBody());
            model.put("page.properties", new SimpleHash(htmlPage.getProperties()));

            // finally, render it
            template.process(model, response.getWriter());
        } catch (Exception e) {
            String msg = "Error applying decorator to request: " + request.getRequestURL() + "?" + request.getQueryString() + " with message:" + e.getMessage();
            LOG.error(msg, e);
            throw new ServletException(msg, e);
        } finally {
            UtilTimerStack.pop(timerKey);
        }
    }
