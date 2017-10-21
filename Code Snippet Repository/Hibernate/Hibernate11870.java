	@Override
	public boolean supports(SpatialFunction function) {
		switch ( function ) {
			case boundary:
			case relate:
			case distance:
			case buffer:
			case convexhull:
			case difference:
			case symdifference:
			case intersection:
			case geomunion:
			case dwithin:
			case transform:
			case extent:
				return false;
			default:
				return true;
		}
	}
