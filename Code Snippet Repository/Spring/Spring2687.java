	@Nullable
	public static BindingResult getBindingResult(Map<?, ?> model, String name) {
		Assert.notNull(model, "Model map must not be null");
		Assert.notNull(name, "Name must not be null");
		Object attr = model.get(BindingResult.MODEL_KEY_PREFIX + name);
		if (attr != null && !(attr instanceof BindingResult)) {
			throw new IllegalStateException("BindingResult attribute is not of type BindingResult: " + attr);
		}
		return (BindingResult) attr;
	}
