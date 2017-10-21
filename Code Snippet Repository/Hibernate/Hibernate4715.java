	@Override
	@SuppressWarnings("")
	public Map getEntries() {
		final Map map = new HashMap();
		for ( Object o : this.region.toMap().entrySet() ) {
			Map.Entry me = (Map.Entry) o;
			map.put( accessStrategy.getNaturalIdValues(me.getKey()), me.getValue() );
		}
		return map;
	}
