    public ScopesHashModel buildTemplateModel(ValueStack stack, Object action, ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, ObjectWrapper wrapper) {
        ScopesHashModel model = buildScopesHashModel(servletContext, request, response, wrapper, stack);
        populateContext(model, stack, action, request, response);
        if (tagLibraries != null) {
            for (String prefix : tagLibraries.keySet()) {
                model.put(prefix, tagLibraries.get(prefix).getModels(stack, request, response));
            }
        }

        //place the model in the request using the special parameter.  This can be retrieved for freemarker and velocity.
        request.setAttribute(ATTR_TEMPLATE_MODEL, model);

        return model;
    }
