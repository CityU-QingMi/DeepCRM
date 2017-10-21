    public int doStartTag() throws JspException {
        ValueStack stack = getStack();
        component = getBean(stack, (HttpServletRequest) pageContext.getRequest(), (HttpServletResponse) pageContext.getResponse());
        Container container = (Container) stack.getContext().get(ActionContext.CONTAINER);
        container.inject(component);
        
        populateParams();
        boolean evalBody = component.start(pageContext.getOut());

        if (evalBody) {
            return component.usesBody() ? EVAL_BODY_BUFFERED : EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
