	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder("Validation failed for argument at index ")
			.append(this.parameter.getParameterIndex()).append(" in method: ")
			.append(this.parameter.getExecutable().toGenericString())
			.append(", with ").append(this.bindingResult.getErrorCount()).append(" error(s): ");
		for (ObjectError error : this.bindingResult.getAllErrors()) {
			sb.append("[").append(error).append("] ");
		}
		return sb.toString();
	}
