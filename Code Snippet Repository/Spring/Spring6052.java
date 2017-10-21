	@Test
	public void wideningPrimitiveConversion_8224() throws Exception {

		class WideningPrimitiveConversion {
			public int getX(long i) {
				return 10;
			}
		}

		final Integer INTEGER_VALUE = Integer.valueOf(7);
		WideningPrimitiveConversion target = new WideningPrimitiveConversion();
		EvaluationContext emptyEvalContext = new StandardEvaluationContext();

		List<TypeDescriptor> args = new ArrayList<>();
		args.add(TypeDescriptor.forObject(INTEGER_VALUE));

		MethodExecutor me = new ReflectiveMethodResolver(true).resolve(emptyEvalContext, target, "getX", args);
		final int actual = (Integer) me.execute(emptyEvalContext, target, INTEGER_VALUE).getValue();

		final int compiler = target.getX(INTEGER_VALUE);
		assertEquals(compiler, actual);
	}
