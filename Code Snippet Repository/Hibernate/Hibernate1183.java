	@Override
	public Object destructure(Object structured, SessionFactoryImplementor factory) {
		final Map<?,?> map = (Map<?,?>) structured;
		final Serializable[] state = new Serializable[ map.size()*2 ];
		int i = 0;
		for ( Map.Entry me : map.entrySet() ) {
			state[i++] = (Serializable) me.getKey();
			state[i++] = (Serializable) me.getValue();
		}
		return new CollectionCacheEntry(state);
	}
