		@RequestMapping("")
		public String myHandle(@ModelAttribute("myCommand") TestBean tb, BindingResult errors, ModelMap model) {
			FieldError error = errors.getFieldError("age");
			assertNotNull("Must have field error for age property", error);
			assertEquals("value2", error.getRejectedValue());
			if (!model.containsKey("myKey")) {
				model.addAttribute("myKey", "myValue");
			}
			return "myView";
		}
