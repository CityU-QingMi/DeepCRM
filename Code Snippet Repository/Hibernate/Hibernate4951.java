	protected Instantiator buildInstantiator(Component component) {
		if ( component.isEmbedded() && ReflectHelper.isAbstractClass( this.componentClass ) ) {
			return new ProxiedInstantiator( this.componentClass );
		}
		if ( optimizer == null ) {
			return new PojoInstantiator( this.componentClass, null );
		}
		else {
			return new PojoInstantiator( this.componentClass, optimizer.getInstantiationOptimizer() );
		}
	}
