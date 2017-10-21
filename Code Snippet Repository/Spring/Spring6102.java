	@Test
	public void test_binaryPlusWithTimeConverted() {

		final SimpleDateFormat format = new SimpleDateFormat("hh :--: mm :--: ss", Locale.ENGLISH);

		GenericConversionService conversionService = new GenericConversionService();
		conversionService.addConverter(new Converter<Time, String>() {
			@Override
			public String convert(Time source) {
				return format.format(source);
			}
		});

		StandardEvaluationContext evaluationContextConverter = new StandardEvaluationContext();
		evaluationContextConverter.setTypeConverter(new StandardTypeConverter(conversionService));

		ExpressionState expressionState = new ExpressionState(evaluationContextConverter);

		Time time = new Time(new Date().getTime());

		VariableReference var = new VariableReference("timeVar", -1);
		var.setValue(expressionState, time);

		StringLiteral n2 = new StringLiteral("\" is now\"", -1, "\" is now\"");
		OpPlus o = new OpPlus(-1, var, n2);
		TypedValue value = o.getValueInternal(expressionState);

		assertEquals(String.class, value.getTypeDescriptor().getObjectType());
		assertEquals(String.class, value.getTypeDescriptor().getType());
		assertEquals(format.format(time) + " is now", value.getValue());
	}
