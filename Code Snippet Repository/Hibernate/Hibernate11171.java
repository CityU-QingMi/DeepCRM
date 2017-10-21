	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof GreetingPO) ) {
			return false;
		}

		GreetingPO that = (GreetingPO) o;

		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		if ( theGreeting != null ? !theGreeting.equals( that.theGreeting ) : that.theGreeting != null ) {
			return false;
		}

		return true;
	}
