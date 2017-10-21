    public boolean render(InternalContextAdapter ctx, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        // get the bean
        ValueStack stack = (ValueStack) ctx.get("stack");
        HttpServletRequest req = (HttpServletRequest) stack.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse res = (HttpServletResponse) stack.getContext().get(ServletActionContext.HTTP_RESPONSE);
        Component bean = getBean(stack, req, res);
        Container container = (Container) stack.getContext().get(ActionContext.CONTAINER);
        container.inject(bean);
        // get the parameters
        Map params = createPropertyMap(ctx, node);
        bean.copyParams(params);
        //bean.addAllParameters(params);
        bean.start(writer);

        if (getType() == BLOCK) {
            Node body = node.jjtGetChild(node.jjtGetNumChildren() - 1);
            body.render(ctx, writer);
        }

        bean.end(writer, "");
        return true;
    }
