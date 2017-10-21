	private ExchangeFunction initExchangeFunction() {
		if (this.exchangeFunction != null) {
			return this.exchangeFunction;
		}
		else if (this.connector != null) {
			return ExchangeFunctions.create(this.connector, this.exchangeStrategies);
		}

		else {
			return ExchangeFunctions.create(new ReactorClientHttpConnector(), this.exchangeStrategies);
		}
	}
