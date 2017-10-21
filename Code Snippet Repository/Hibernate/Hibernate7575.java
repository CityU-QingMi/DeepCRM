	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( day == null ) ? 0 : day.hashCode() );
		result = prime * result + ( ( hour == null ) ? 0 : hour.hashCode() );
		result = prime * result + ( ( minute == null ) ? 0 : minute.hashCode() );
		result = prime * result + ( ( month == null ) ? 0 : month.hashCode() );
		result = prime * result + ( ( second == null ) ? 0 : second.hashCode() );
		result = prime * result + ( ( year == null ) ? 0 : year.hashCode() );
		return result;
	}
