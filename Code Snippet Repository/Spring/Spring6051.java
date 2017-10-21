	@Test
	public void conversionPriority_8224() throws Exception {

		@SuppressWarnings("unused")
		class ConversionPriority1 {
			public int getX(Number i) {
				return 20;
			}
			public int getX(int i) {
				return 10;
			}
		}

		@SuppressWarnings("unused")
		class ConversionPriority2 {
			public int getX(int i) {
				return 10;
			}
			public int getX(Number i) {
				return 20;
			}
		}

		final Integer INTEGER = Integer.valueOf(7);

		EvaluationContext emptyEvalContext = new StandardEvaluationContext();

		List<TypeDescriptor> args = new ArrayList<>();
		args.add(TypeDescriptor.forObject(new Integer(42)));

		ConversionPriority1 target = new ConversionPriority1();
		MethodExecutor me = new ReflectiveMethodResolver(true).resolve(emptyEvalContext, target, "getX", args);
		// MethodInvoker chooses getX(int i) when passing Integer
		final int actual = (Integer) me.execute(emptyEvalContext, target, new Integer(42)).getValue();
		// Compiler chooses getX(Number i) when passing Integer
		final int compiler = target.getX(INTEGER);
		// Fails!
		assertEquals(compiler, actual);

		ConversionPriority2 target2 = new ConversionPriority2();
		MethodExecutor me2 = new ReflectiveMethodResolver(true).resolve(emptyEvalContext, target2, "getX", args);
		// MethodInvoker chooses getX(int i) when passing Integer
		int actual2 = (Integer) me2.execute(emptyEvalContext, target2, new Integer(42)).getValue();
		// Compiler chooses getX(Number i) when passing Integer
		int compiler2 = target2.getX(INTEGER);
		// Fails!
		assertEquals(compiler2, actual2);

	}
