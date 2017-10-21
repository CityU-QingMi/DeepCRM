	@Override
	@SuppressWarnings({ "" })
	public <K, V> MapAttribute<? super X, K, V> getMap(String name, Class<K> keyType, Class<V> valueType) {
		PluralAttribute<? super X, ?, ?> attribute = getPluralAttribute( name );
		if ( attribute == null && getSupertype() != null ) {
			attribute = getSupertype().getPluralAttribute( name );
		}
		checkMapValueType( attribute, name, valueType );
		final MapAttribute<? super X, K, V> mapAttribute = ( MapAttribute<? super X, K, V> ) attribute;
		checkMapKeyType( mapAttribute, name, keyType );
		return mapAttribute;
	}
