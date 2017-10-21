	@Nullable
	public static Object unwrapOptional(@Nullable Object obj) {
		if (obj instanceof Optional) {
			Optional<?> optional = (Optional<?>) obj;
			if (!optional.isPresent()) {
				return null;
			}
			Object result = optional.get();
			Assert.isTrue(!(result instanceof Optional), "Multi-level Optional usage not supported");
			return result;
		}
		return obj;
	}
