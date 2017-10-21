		@Override
		public Template getTemplate(String name, final Locale locale) throws IOException {
			if (name.equals("templateName") || name.equals("prefix_test_suffix")) {
				return new Template(name, new StringReader("test"), this) {
					@Override
					public void process(Object model, Writer writer) throws TemplateException, IOException {
						assertEquals(Locale.US, locale);
						assertTrue(model instanceof AllHttpScopesHashModel);
						AllHttpScopesHashModel fmModel = (AllHttpScopesHashModel) model;
						assertEquals("myvalue", fmModel.get("myattr").toString());
					}
				};
			}
			else {
				throw new FileNotFoundException();
			}
		}
