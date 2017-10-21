	public String toString()
	{
	    final String TAB = "    ";
	    
	    String retValue = "";
	    
	    retValue = "PlanetCheatSheet ( "
	        + super.toString() + TAB
	        + "planet = " + this.planet + TAB
	        + "mass = " + this.mass + TAB
	        + "radius = " + this.radius + TAB
	        + "numberOfInhabitants = " + this.numberOfInhabitants + TAB
	        + " )";
	
	    return retValue;
	}	
