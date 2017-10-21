	@Override
	protected boolean hasError(HttpStatus statusCode) {
		if (this.statusMapping.containsKey(statusCode)) {
			return this.statusMapping.get(statusCode) != null;
		}
		else if (this.seriesMapping.containsKey(statusCode.series())) {
			return this.seriesMapping.get(statusCode.series()) != null;
		}
		else {
			return super.hasError(statusCode);
		}
	}
