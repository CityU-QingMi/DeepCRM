	@Override
	public List<HttpMessageWriter<?>> getWriters() {
		List<HttpMessageWriter<?>> result = new ArrayList<>();

		result.addAll(this.defaultCodecs.getTypedWriters());
		result.addAll(this.customCodecs.getTypedWriters());

		result.addAll(this.defaultCodecs.getObjectWriters());
		result.addAll(this.customCodecs.getObjectWriters());

		result.addAll(this.defaultCodecs.getCatchAllWriters());
		return result;
	}
