	public Object instantiate(String entityName, EntityMode entityMode, Serializable id) {
		if ( entityMode == EntityMode.POJO ) {
			if ( Customer.class.getName().equals( entityName ) ) {
				return ProxyHelper.newCustomerProxy( id );
			}
			else if ( Company.class.getName().equals( entityName ) ) {
				return ProxyHelper.newCompanyProxy( id );
			}
		}
		return super.instantiate( entityName, entityMode, id );
	}
