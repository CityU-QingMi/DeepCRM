	@Override
	public void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {
		if (this.statusMapping.containsKey(statusCode)) {
			extract(this.statusMapping.get(statusCode), response);
		}
		else if (this.seriesMapping.containsKey(statusCode.series())) {
			extract(this.seriesMapping.get(statusCode.series()), response);
		}
		else {
			super.handleError(response, statusCode);
		}
	}
