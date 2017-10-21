		@Override
		public View resolveViewName(final String viewName, Locale locale) throws Exception {
			return new View() {
				@Override
				public String getContentType() {
					return null;
				}

				@Override
				@SuppressWarnings({"unchecked", "deprecation", "rawtypes"})
				public void render(@Nullable Map model, HttpServletRequest request, HttpServletResponse response)
						throws Exception {
					TestBean tb = (TestBean) model.get("testBean");
					if (tb == null) {
						tb = (TestBean) model.get("myCommand");
					}
					if (tb.getName() != null && tb.getName().endsWith("myDefaultName")) {
						assertEquals(107, tb.getDate().getYear());
					}
					Errors errors = (Errors) model.get(BindingResult.MODEL_KEY_PREFIX + "testBean");
					if (errors == null) {
						errors = (Errors) model.get(BindingResult.MODEL_KEY_PREFIX + "myCommand");
					}
					if (errors.hasFieldErrors("date")) {
						throw new IllegalStateException();
					}
					if (model.containsKey("ITestBean")) {
						assertTrue(model.get(BindingResult.MODEL_KEY_PREFIX + "ITestBean") instanceof Errors);
					}
					List<TestBean> testBeans = (List<TestBean>) model.get("testBeanList");
					if (errors.hasFieldErrors("age")) {
						response.getWriter()
								.write(viewName + "-" + tb.getName() + "-" + errors.getFieldError("age").getCode() +
										"-" + testBeans.get(0).getName() + "-" + model.get("myKey") +
										(model.containsKey("yourKey") ? "-" + model.get("yourKey") : ""));
					}
					else {
						response.getWriter().write(viewName + "-" + tb.getName() + "-" + tb.getAge() + "-" +
								errors.getFieldValue("name") + "-" + errors.getFieldValue("age"));
					}
				}
			};
		}
