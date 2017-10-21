	@Override
	public Object replaceElements(
		Object original,
		Object target,
		Object owner, 
		Map copyCache,
		SharedSessionContractImplementor session) throws HibernateException {
		
		int length = Array.getLength(original);
		if ( length!=Array.getLength(target) ) {
			//note: this affects the return value!
			target=instantiateResult(original);
		}
		
		Type elemType = getElementType( session.getFactory() );
		for ( int i=0; i<length; i++ ) {
			Array.set( target, i, elemType.replace( Array.get(original, i), null, session, owner, copyCache ) );
		}
		
		return target;
	
	}
