	@Nullable
	public Errors getErrors(String name, boolean htmlEscape) {
		if (this.errorsMap == null) {
			this.errorsMap = new HashMap<>();
		}

		Errors errors = this.errorsMap.get(name);
		if (errors == null) {
			errors = getModelObject(BindingResult.MODEL_KEY_PREFIX + name);
			if (errors == null) {
				return null;
			}
		}

		if (errors instanceof BindException) {
			errors = ((BindException) errors).getBindingResult();
		}

		if (htmlEscape && !(errors instanceof EscapedErrors)) {
			errors = new EscapedErrors(errors);
		}
		else if (!htmlEscape && errors instanceof EscapedErrors) {
			errors = ((EscapedErrors) errors).getSource();
		}

		this.errorsMap.put(name, errors);
		return errors;
	}
