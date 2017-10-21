	@Test
	public void varargsAndPrimitives_SPR8174() throws Exception {
		EvaluationContext emptyEvalContext = new StandardEvaluationContext();
		List<TypeDescriptor> args = new ArrayList<>();

		args.add(TypeDescriptor.forObject(34L));
		ReflectionUtil<Integer> ru = new ReflectionUtil<>();
		MethodExecutor me = new ReflectiveMethodResolver().resolve(emptyEvalContext, ru, "methodToCall", args);

		args.set(0, TypeDescriptor.forObject(23));
		me = new ReflectiveMethodResolver().resolve(emptyEvalContext, ru, "foo", args);
		me.execute(emptyEvalContext, ru, 45);

		args.set(0, TypeDescriptor.forObject(23f));
		me = new ReflectiveMethodResolver().resolve(emptyEvalContext, ru, "foo", args);
		me.execute(emptyEvalContext, ru, 45f);

		args.set(0, TypeDescriptor.forObject(23d));
		me = new ReflectiveMethodResolver().resolve(emptyEvalContext, ru, "foo", args);
		me.execute(emptyEvalContext, ru, 23d);

		args.set(0, TypeDescriptor.forObject((short) 23));
		me = new ReflectiveMethodResolver().resolve(emptyEvalContext, ru, "foo", args);
		me.execute(emptyEvalContext, ru, (short) 23);

		args.set(0, TypeDescriptor.forObject(23L));
		me = new ReflectiveMethodResolver().resolve(emptyEvalContext, ru, "foo", args);
		me.execute(emptyEvalContext, ru, 23L);

		args.set(0, TypeDescriptor.forObject((char) 65));
		me = new ReflectiveMethodResolver().resolve(emptyEvalContext, ru, "foo", args);
		me.execute(emptyEvalContext, ru, (char) 65);

		args.set(0, TypeDescriptor.forObject((byte) 23));
		me = new ReflectiveMethodResolver().resolve(emptyEvalContext, ru, "foo", args);
		me.execute(emptyEvalContext, ru, (byte) 23);

		args.set(0, TypeDescriptor.forObject(true));
		me = new ReflectiveMethodResolver().resolve(emptyEvalContext, ru, "foo", args);
		me.execute(emptyEvalContext, ru, true);

		// trickier:
		args.set(0, TypeDescriptor.forObject(12));
		args.add(TypeDescriptor.forObject(23f));
		me = new ReflectiveMethodResolver().resolve(emptyEvalContext, ru, "bar", args);
		me.execute(emptyEvalContext, ru, 12, 23f);
	}
