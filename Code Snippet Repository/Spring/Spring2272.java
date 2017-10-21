	@Override
	protected Object[] resolveArguments(@Nullable Object[] args, Locale locale) {
		if (ObjectUtils.isEmpty(args)) {
			return super.resolveArguments(args, locale);
		}
		List<Object> resolvedArgs = new ArrayList<>(args.length);
		for (Object arg : args) {
			if (arg instanceof MessageSourceResolvable) {
				resolvedArgs.add(getMessage((MessageSourceResolvable) arg, locale));
			}
			else {
				resolvedArgs.add(arg);
			}
		}
		return resolvedArgs.toArray(new Object[resolvedArgs.size()]);
	}
