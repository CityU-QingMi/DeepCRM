	public Object instantiate(Serializable id) {
		if ( Person.class.getName().equals( entityName ) ) {
			return ProxyHelper.newPersonProxy( id );
		}
		if ( Customer.class.getName().equals( entityName ) ) {
			return ProxyHelper.newCustomerProxy( id );
		}
		else if ( Company.class.getName().equals( entityName ) ) {
			return ProxyHelper.newCompanyProxy( id );
		}
		else if ( Address.class.getName().equals( entityName ) ) {
			return ProxyHelper.newAddressProxy( id );
		}
		else {
			throw new IllegalArgumentException( "unknown entity for instantiation [" + entityName + "]" );
		}
	}
