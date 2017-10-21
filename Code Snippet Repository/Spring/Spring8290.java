	@Override
	public WebTestClient build() {
		ClientHttpConnector connectorToUse = this.connector;
		if (connectorToUse == null) {
			Assert.state(this.httpHandlerBuilder != null, "No WebHttpHandlerBuilder available");
			connectorToUse = new HttpHandlerConnector(this.httpHandlerBuilder.build());
		}

		DefaultWebTestClientBuilder webTestClientBuilder = new DefaultWebTestClientBuilder(
				this.webClientBuilder.clone(), this.httpHandlerBuilder,
				this.connector, this.responseTimeout);

		return new DefaultWebTestClient(this.webClientBuilder,
				connectorToUse, this.responseTimeout, webTestClientBuilder);
	}
