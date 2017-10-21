		@Override
		public View resolveViewName(final String viewName, Locale locale) throws Exception {
			return new AbstractView () {
				@Override
				public String getContentType() {
					return null;
				}
				@Override
				protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
						HttpServletResponse response) throws Exception {
					for (String key : attrsToValidate.keySet()) {
						assertTrue("Model should contain attribute named " + key, model.containsKey(key));
						assertEquals(attrsToValidate.get(key), model.get(key));
						validatedAttrCount++;
					}
				}
			};
		}
