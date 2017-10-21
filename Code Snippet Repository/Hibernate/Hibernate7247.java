	public String toString()
	{
		StringBuilder buffer = new StringBuilder();
		
		buffer.append( name + " id: " + nodeID );
		if ( route != null ) {
			buffer.append( " route name: " )
					.append( route.getName() )
					.append( " tour name: " )
					.append( ( tour == null ? "null" : tour.getName() ) );
		}
		if ( pickupTransports != null ) {
			for (Iterator it = pickupTransports.iterator(); it.hasNext();) {
				buffer.append("Pickup transports: " + it.next());
			}
		}
		
		if ( deliveryTransports != null ) {
			for (Iterator it = deliveryTransports.iterator(); it.hasNext();) {
				buffer.append("Delviery transports: " + it.next());
			}
		}
		
		return buffer.toString();
	}
