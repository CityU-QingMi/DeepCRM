	public String toString() {
		String result = "FooComponent: " + name + "=" + count;
		result+="; dates=[";
		if ( importantDates!=null) {
			for ( int i=0; i<importantDates.length; i++ ) {
				result+=(i==0 ?"":", ") + importantDates[i];
			}
		}
		result+="]";
		if ( subcomponent!=null ) {
			result+= " (" + subcomponent + ")";
		}
		return result;
	}
