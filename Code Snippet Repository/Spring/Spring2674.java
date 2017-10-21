	@Override
	public List<FieldError> getFieldErrors() {
		List<FieldError> result = new LinkedList<>();
		for (ObjectError objectError : this.errors) {
			if (objectError instanceof FieldError) {
				result.add((FieldError) objectError);
			}
		}
		return Collections.unmodifiableList(result);
	}
