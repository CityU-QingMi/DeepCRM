	@Override
	public String getMessage() {
		MethodParameter parameter = getMethodParameter();
		Assert.state(parameter != null, "No MethodParameter");
		StringBuilder sb = new StringBuilder("Validation failed for argument at index ")
				.append(parameter.getParameterIndex()).append(" in method: ")
				.append(parameter.getExecutable().toGenericString())
				.append(", with ").append(this.bindingResult.getErrorCount()).append(" error(s): ");
		for (ObjectError error : this.bindingResult.getAllErrors()) {
			sb.append("[").append(error).append("] ");
		}
		return sb.toString();
	}
