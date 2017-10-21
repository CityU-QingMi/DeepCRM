    public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {
        HTMLPage htmlPage = (HTMLPage) request.getAttribute(RequestConstants.PAGE);
        String template;

        context.put("base", request.getContextPath());
        // For backwards compatibility with apps that used the old VelocityDecoratorServlet
        // that extended VelocityServlet instead of VelocityViewServlet
        context.put("req", request);
        context.put("res", response);

        if (htmlPage == null) {
            context.put("title", "Title?");
            context.put("body", "<p>Body?</p>");
            context.put("head", "<!-- head -->");
            template = request.getServletPath();
        } else {
            try {
                context.put("title", OutputConverter.convert(htmlPage.getTitle()));
                {
                    StringWriter buffer = new StringWriter();
                    htmlPage.writeBody(OutputConverter.getWriter(buffer));
                    context.put("body", buffer.toString());
                }
                {
                    StringWriter buffer = new StringWriter();
                    htmlPage.writeHead(OutputConverter.getWriter(buffer));
                    context.put("head", buffer.toString());
                }
            } catch (IOException e) {
                LOG.error("IOException handle request template", e);
            }
            context.put("page", htmlPage);
            DecoratorMapper decoratorMapper = getDecoratorMapper();
            Decorator decorator = decoratorMapper.getDecorator(request, htmlPage);
            template = decorator.getPage();
        }

        return getTemplate(template);
    }
