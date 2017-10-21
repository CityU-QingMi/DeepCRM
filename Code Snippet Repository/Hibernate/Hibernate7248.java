	public String toString()
	{
		StringBuilder buffer = new StringBuilder();
		
		buffer.append("Route name: " + name + " id: " + routeID + " transientField: " + transientField + "\n");
		for (Iterator it = nodes.iterator(); it.hasNext();) {
			buffer.append("Node: " + it.next() );
		}
		
		for (Iterator it = vehicles.iterator(); it.hasNext();) {
			buffer.append("Vehicle: " + it.next() );
		}

		return buffer.toString();
	}
