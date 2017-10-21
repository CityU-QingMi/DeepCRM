	public void testConditionalParseExpression()  throws Exception {
		ValueStack oldStack = ActionContext.getContext().getValueStack();
		try {
			OgnlValueStack stack = (OgnlValueStack) container.getInstance(ValueStackFactory.class).createValueStack();
			stack.getContext().put(ActionContext.CONTAINER, container);
			stack.getContext().put("something", "somevalue");
			ActionContext.getContext().setValueStack(stack);
			ValidatorSupport validator = new ValidatorSupport() {
				public void validate(Object object) throws ValidationException {
				}
			};
            validator.setValueStack(ActionContext.getContext().getValueStack());

			String result1 = validator.parse("${#something}", String.class).toString();

			assertEquals(result1, "somevalue");
		}
		finally {
			ActionContext.getContext().setValueStack(oldStack);
		}
	}
