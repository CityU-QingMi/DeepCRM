	@Override
	public boolean equals(Object obj) {
		if (! obj.getClass().equals( Location.class )) {
			return false;
		}
		
		Location loc = (Location) obj;
		if (name != null ? !name.equals(loc.name) : loc.name != null) return false;
		if (type != null ? !type.equals(loc.type) : loc.type != null) return false;
		
		return true;
	}
