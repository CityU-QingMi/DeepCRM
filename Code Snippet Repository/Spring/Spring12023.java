	@Override
	public Validator getValidator() {
		Validator selected = null;
		for (WebMvcConfigurer configurer : this.delegates) {
			Validator validator = configurer.getValidator();
			if (validator != null) {
				if (selected != null) {
					throw new IllegalStateException("No unique Validator found: {" +
							selected + ", " + validator + "}");
				}
				selected = validator;
			}
		}
		return selected;
	}
