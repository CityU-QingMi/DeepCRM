    public void testNoBooleanExpression() throws Exception {
        Mock mock = new Mock(ValidationAware.class);
        mock.expect("addActionError", C.ANY_ARGS);

        ExpressionValidator ev = new ExpressionValidator();
        ev.setValidatorContext(new DelegatingValidatorContext(mock.proxy(), tpf));
        ev.setExpression("{top}");
        ev.setValueStack(ActionContext.getContext().getValueStack());
        ev.validate("Hello"); // {top} will evaluate to Hello that is not a Boolean
        mock.verify();
    }
