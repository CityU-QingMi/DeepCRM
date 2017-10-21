	@Deprecated
	public Map<String, TypedValue> collectNamedParameterBindings() {
		Map<String, TypedValue> collectedBindings = new HashMap<>();
		for ( Map.Entry<QueryParameter, QueryParameterBinding> entry : parameterBindingMap.entrySet() ) {
			if ( entry.getKey().getName() == null ) {
				continue;
			}

			Type bindType = entry.getValue().getBindType();
			if ( bindType == null ) {
				log.debugf( "Binding for named-parameter [%s] did not define type", entry.getKey().getName() );
				bindType = SerializableType.INSTANCE;
			}

			collectedBindings.put(
					entry.getKey().getName(),
					new TypedValue( bindType, entry.getValue().getBindValue() )
			);
		}

		return collectedBindings;
	}
