    public Writer getWriter(Writer writer, Map params)
        throws TemplateModelException, IOException {
        Component bean = getBean();
        Container container = (Container) stack.getContext().get(ActionContext.CONTAINER);
        container.inject(bean);

        Map unwrappedParameters = unwrapParameters(params);
        bean.copyParams(unwrappedParameters);

        return new CallbackWriter(bean, writer);
    }
