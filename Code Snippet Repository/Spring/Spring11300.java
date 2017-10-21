	@Override
	public boolean supportsParameter(MethodParameter param) {
		if (checkAnnotatedParamNoReactiveWrapper(param, RequestParam.class, this::singleParam)) {
			return true;
		}
		else if (this.useDefaultResolution) {
			return checkParameterTypeNoReactiveWrapper(param, BeanUtils::isSimpleProperty) ||
					BeanUtils.isSimpleProperty(param.nestedIfOptional().getNestedParameterType());
		}
		return false;
	}
