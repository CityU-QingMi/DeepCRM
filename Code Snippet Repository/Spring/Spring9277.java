	@Override
	public List<HttpMessageReader<?>> getReaders() {
		List<HttpMessageReader<?>> result = new ArrayList<>();

		result.addAll(this.defaultCodecs.getTypedReaders());
		result.addAll(this.customCodecs.getTypedReaders());

		result.addAll(this.defaultCodecs.getObjectReaders());
		result.addAll(this.customCodecs.getObjectReaders());

		result.addAll(this.defaultCodecs.getCatchAllReaders());
		return result;
	}
