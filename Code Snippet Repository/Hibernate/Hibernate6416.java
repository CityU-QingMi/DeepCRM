	public void arriveToPoi(Poi poi, Date time) {
		if ( poiArrival == null ) {
			poiArrival = new HashMap<Poi, PoiArrival>();
		}

		PoiArrival arrival = poiArrival.get( poi );
		if ( arrival == null ) {
			arrival = new PoiArrival();
			poiArrival.put( poi, arrival );
		}

		arrival.setArriveTime( time );
	}
