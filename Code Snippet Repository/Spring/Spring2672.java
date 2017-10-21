	@Override
	public List<ObjectError> getGlobalErrors() {
		List<ObjectError> result = new LinkedList<>();
		for (ObjectError objectError : this.errors) {
			if (!(objectError instanceof FieldError)) {
				result.add(objectError);
			}
		}
		return Collections.unmodifiableList(result);
	}
