	protected void printModelAndView(@Nullable ModelAndView mav) throws Exception {
		this.printer.printValue("View name", (mav != null) ? mav.getViewName() : null);
		this.printer.printValue("View", (mav != null) ? mav.getView() : null);
		if (mav == null || mav.getModel().size() == 0) {
			this.printer.printValue("Model", null);
		}
		else {
			for (String name : mav.getModel().keySet()) {
				if (!name.startsWith(BindingResult.MODEL_KEY_PREFIX)) {
					Object value = mav.getModel().get(name);
					this.printer.printValue("Attribute", name);
					this.printer.printValue("value", value);
					Errors errors = (Errors) mav.getModel().get(BindingResult.MODEL_KEY_PREFIX + name);
					if (errors != null) {
						this.printer.printValue("errors", errors.getAllErrors());
					}
				}
			}
		}
	}
