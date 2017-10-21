	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		DataPoint dataPoint = (DataPoint) o;

		if ( sequence != dataPoint.sequence ) {
			return false;
		}
		if ( description != null ? !description.equals( dataPoint.description ) : dataPoint.description != null ) {
			return false;
		}
		if ( x != null ? !x.equals( dataPoint.x ) : dataPoint.x != null ) {
			return false;
		}
		if ( y != null ? !y.equals( dataPoint.y ) : dataPoint.y != null ) {
			return false;
		}

		return true;
	}
