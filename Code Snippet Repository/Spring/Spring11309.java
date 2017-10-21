	protected Mono<Void> resolveAsyncAttributes(Map<String, Object> model) {

		List<String> names = new ArrayList<>();
		List<Mono<?>> valueMonos = new ArrayList<>();

		for (Map.Entry<String, ?> entry : model.entrySet()) {
			Object value =  entry.getValue();
			if (value == null) {
				continue;
			}
			ReactiveAdapter adapter = this.adapterRegistry.getAdapter(null, value);
			if (adapter != null) {
				names.add(entry.getKey());
				if (adapter.isMultiValue()) {
					Flux<Object> fluxValue = Flux.from(adapter.toPublisher(value));
					valueMonos.add(fluxValue.collectList().defaultIfEmpty(Collections.emptyList()));
				}
				else {
					Mono<Object> monoValue = Mono.from(adapter.toPublisher(value));
					valueMonos.add(monoValue.defaultIfEmpty(NO_VALUE));
				}
			}
		}

		if (names.isEmpty()) {
			return Mono.empty();
		}

		return Mono.zip(valueMonos,
				values -> {
					for (int i=0; i < values.length; i++) {
						if (values[i] != NO_VALUE) {
							model.put(names.get(i), values[i]);
						}
						else {
							model.remove(names.get(i));
						}
					}
					return NO_VALUE;
				})
				.then();
	}
