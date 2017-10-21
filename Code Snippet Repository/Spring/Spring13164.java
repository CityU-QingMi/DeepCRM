		@Override
		public View resolveViewName(final String viewName, Locale locale) throws Exception {
			return new View() {
				@Override
				public String getContentType() {
					return null;
				}
				@Override
				public void render(@Nullable Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) {
					request.setAttribute("viewName", viewName);
					request.getSession().setAttribute("model", model);
				}
			};
		}
