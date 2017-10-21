	@Override
	protected WebExchangeDataBinder initDataBinder(WebExchangeDataBinder dataBinder,
			ServerWebExchange exchange) {

		this.binderMethods.stream()
				.filter(binderMethod -> {
					InitBinder ann = binderMethod.getMethodAnnotation(InitBinder.class);
					Assert.state(ann != null, "No InitBinder annotation");
					Collection<String> names = Arrays.asList(ann.value());
					return (names.size() == 0 || names.contains(dataBinder.getObjectName()));
				})
				.forEach(method -> invokeBinderMethod(dataBinder, exchange, method));

		return dataBinder;
	}
