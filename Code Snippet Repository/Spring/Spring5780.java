	@Override
	public TypedValue execute(EvaluationContext context, Object... arguments) throws AccessException {
		try {
			ReflectionHelper.convertArguments(
					context.getTypeConverter(), arguments, this.ctor, this.varargsPosition);
			if (this.ctor.isVarArgs()) {
				arguments = ReflectionHelper.setupArgumentsForVarargsInvocation(
						this.ctor.getParameterTypes(), arguments);
			}
			ReflectionUtils.makeAccessible(this.ctor);
			return new TypedValue(this.ctor.newInstance(arguments));
		}
		catch (Exception ex) {
			throw new AccessException("Problem invoking constructor: " + this.ctor, ex);
		}
	}
