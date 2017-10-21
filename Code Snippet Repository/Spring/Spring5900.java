	@Test
	public void invokeMethodWithoutConversion() throws Exception {
		final BytesService service = new BytesService();
		byte[] bytes = new byte[100];
		StandardEvaluationContext context = new StandardEvaluationContext(bytes);
		context.setBeanResolver(new BeanResolver() {
			@Override
			public Object resolve(EvaluationContext context, String beanName) throws AccessException {
				if ("service".equals(beanName)) {
					return service;
				}
				return null;
			}
		});
		Expression expression = parser.parseExpression("@service.handleBytes(#root)");
		byte[] outBytes = expression.getValue(context, byte[].class);
		assertSame(bytes, outBytes);
	}
