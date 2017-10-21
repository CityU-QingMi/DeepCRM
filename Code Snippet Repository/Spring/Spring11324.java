	private void updateBindingContext(BindingContext context, ServerWebExchange exchange) {
		Map<String, Object> model = context.getModel().asMap();
		model.keySet().stream()
				.filter(name -> isBindingCandidate(name, model.get(name)))
				.filter(name -> !model.containsKey(BindingResult.MODEL_KEY_PREFIX + name))
				.forEach(name -> {
					WebExchangeDataBinder binder = context.createDataBinder(exchange, model.get(name), name);
					model.put(BindingResult.MODEL_KEY_PREFIX + name, binder.getBindingResult());
				});
	}
