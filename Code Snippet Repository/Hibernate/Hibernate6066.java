	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( !( o instanceof Employee ) ) return false;

		Employee employee = (Employee) o;

		if ( id != null ? !id.equals( employee.id ) : employee.id != null ) return false;
		if ( name != null ? !name.equals( employee.name ) : employee.name != null ) return false;
		if ( salary != null ? !salary.equals( employee.salary ) : employee.salary != null ) return false;

		return true;
	}
