	@Override
	public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

		if (mavContainer != null) {
			mavContainer.setRequestHandled(true);
		}

		Class<?> paramType = parameter.getParameterType();

		// ServletResponse, HttpServletResponse
		if (ServletResponse.class.isAssignableFrom(paramType)) {
			return resolveNativeResponse(webRequest, paramType);
		}

		// ServletResponse required for all further argument types
		return resolveArgument(paramType, resolveNativeResponse(webRequest, ServletResponse.class));
	}
