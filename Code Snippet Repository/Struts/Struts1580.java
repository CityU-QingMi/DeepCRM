	@Override
    protected void setUp() throws Exception {
	    super.setUp();
		ValueStack stack = ActionContext.getContext().getValueStack();
		MockActionInvocation invocation = new MockActionInvocation();
		invocation.setStack(stack);
		ActionContext.getContext().setValueStack(stack);
		ActionContext.getContext().setActionInvocation(invocation);
		
		String[] conversionErrorValue = new String[] { "some value" };
		Map<String, Object> conversionErrors = ActionContext.getContext().getConversionErrors();
		conversionErrors.put("someFieldName", conversionErrorValue);
		conversionErrors.put("xxxsomeFieldName", conversionErrorValue);

		TextProviderFactory tpf = container.getInstance(TextProviderFactory.class);

		action = container.inject(ActionSupport.class);
		validator1 = 
			new InternalRepopulateConversionErrorFieldValidatorSupport();
		validator1.setFieldName("someFieldName");
		validator1.setValidatorContext(new DelegatingValidatorContext(action, tpf));
		
		validator2 = 
			new InternalRepopulateConversionErrorFieldValidatorSupport();
		validator2.setFieldName("someFieldName");
		validator2.setValidatorContext(new DelegatingValidatorContext(action, tpf) {
			@Override
            public String getFullFieldName(String fieldName) {
				return "xxx"+fieldName;
			}
		});
	}
