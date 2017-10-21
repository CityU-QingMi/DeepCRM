		@RequestMapping("")
		public void handle(@ModelAttribute TestBean testBean,
				Errors errors,
				@ModelAttribute TestPrincipal modelPrinc,
				OtherPrincipal requestPrinc,
				Writer writer) throws IOException {
			assertNull(testBean);
			assertNotNull(modelPrinc);
			assertNotNull(requestPrinc);
			assertFalse(errors.hasErrors());
			errors.reject("myCode");
			writer.write("myView");
		}
