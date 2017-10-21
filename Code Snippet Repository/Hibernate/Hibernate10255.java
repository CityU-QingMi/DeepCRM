	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof Component2) ) {
			return false;
		}

		Component2 that = (Component2) o;

		if ( str5 != null ? !str5.equals( that.str5 ) : that.str5 != null ) {
			return false;
		}
		if ( str6 != null ? !str6.equals( that.str6 ) : that.str6 != null ) {
			return false;
		}

		return true;
	}
