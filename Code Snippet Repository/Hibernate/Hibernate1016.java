	@Override
	public <T> void registerStrategyImplementor(Class<T> strategy, String name, Class<? extends T> implementation) {
		Map<String,Class> namedStrategyImplementorMap = namedStrategyImplementorByStrategyMap.get( strategy );
		if ( namedStrategyImplementorMap == null ) {
			namedStrategyImplementorMap = new ConcurrentHashMap<>();
			namedStrategyImplementorByStrategyMap.put( strategy, namedStrategyImplementorMap );
		}

		final Class old = namedStrategyImplementorMap.put( name, implementation );
		if ( old == null ) {
			log.trace(
					String.format(
							"Registering named strategy selector [%s] : [%s] -> [%s]",
							strategy.getName(),
							name,
							implementation.getName()
					)
			);
		}
		else {
			log.debug(
					String.format(
							"Registering named strategy selector [%s] : [%s] -> [%s] (replacing [%s])",
							strategy.getName(),
							name,
							implementation.getName(),
							old.getName()
					)
			);
		}
	}
