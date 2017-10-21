		@Override
		@SuppressWarnings("")
		public ModelAndView resolveModelAndView(Method handlerMethod, Class<?> handlerType, Object returnValue,
				ExtendedModelMap implicitModel, NativeWebRequest webRequest) {

			if (returnValue instanceof MySpecialArg) {
				return new ModelAndView(new View() {
					@Override
					public String getContentType() {
						return "text/html";
					}
					@Override
					public void render(@Nullable Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
							throws Exception {
						response.getWriter().write("myValue");
					}

				});
			}
			return UNRESOLVED;
		}
