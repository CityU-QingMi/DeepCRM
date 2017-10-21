	public void testTranslateVariablesWithEvaluator() throws Exception {
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.push(new Object() {
			public String getMyVariable() {
				return "My Variable ";
			}
		});

		TextParseUtil.ParsedValueEvaluator evaluator = new TextParseUtil.ParsedValueEvaluator() {
			public Object evaluate(String parsedValue) {
                return parsedValue + "Something";
            }
		};

		String result = TextParseUtil.translateVariables("Hello ${myVariable}", stack, evaluator);

		assertEquals(result, "Hello My Variable Something");
	}
