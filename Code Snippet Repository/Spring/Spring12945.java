		@ModelAttribute
		public void model(Model model) {
			TestBean modelAttr = new TestBean();
			modelAttr.setName("Set by model method [modelAttr]");
			model.addAttribute("modelAttr", modelAttr);

			modelAttr = new TestBean();
			modelAttr.setName("Set by model method [modelAttrByConvention]");
			model.addAttribute(modelAttr);

			model.addAttribute(new OtherUser());
		}
