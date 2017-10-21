	@Override
	@Nullable
	public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

		Assert.state(mavContainer != null, "Errors/BindingResult argument only supported on regular handler methods");

		ModelMap model = mavContainer.getModel();
		if (model.size() > 0) {
			int lastIndex = model.size()-1;
			String lastKey = new ArrayList<>(model.keySet()).get(lastIndex);
			if (lastKey.startsWith(BindingResult.MODEL_KEY_PREFIX)) {
				return model.get(lastKey);
			}
		}

		throw new IllegalStateException(
				"An Errors/BindingResult argument is expected to be declared immediately after the model attribute, " +
				"the @RequestBody or the @RequestPart arguments to which they apply: " + parameter.getMethod());
	}
