	public Object[] getPropertyValues(Object component) throws HibernateException {
		if ( component == PropertyAccessStrategyBackRefImpl.UNKNOWN ) {
			return new Object[propertySpan];
		}
		else if ( optimizer != null && optimizer.getAccessOptimizer() != null ) {
			return optimizer.getAccessOptimizer().getPropertyValues( component );
		}
		else {
			return super.getPropertyValues( component );
		}
	}
