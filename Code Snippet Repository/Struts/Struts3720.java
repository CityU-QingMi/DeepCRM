    @Override
    public void render(String path, Request request) throws IOException {
        if (path != null) {
            LOG.trace("Rendering freemarker tile [{}]", path);

            ActionContext ctx = readActionContext(request);

            registerTilesBeanModel(ctx);

            FreemarkerResult result = new FreemarkerResult(path);
            result.setWriter(request.getWriter());

            Container container = ctx.getContainer();
            container.inject(result);

            try {
                ActionInvocation invocation = ctx.getActionInvocation();
                result.doExecute(path, invocation);
            } catch (TemplateException e) {
                LOG.error("Exception was thrown during rendering value {}: {}", path, e.getMessage());
                throw new InvalidTemplateException(e);
            }
        } else {
            LOG.error("Path is null, cannot render template!");
            throw new InvalidTemplateException("Cannot render a null template");
        }
    }
