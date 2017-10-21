	public DefaultCacheKeyInvocationContext(AbstractJCacheKeyOperation<A> operation,
			Object target, Object[] args) {
		super(operation, target, args);
		this.keyParameters = operation.getKeyParameters(args);
		if (operation instanceof CachePutOperation) {
			this.valueParameter = ((CachePutOperation) operation).getValueParameter(args);
		}
		else {
			this.valueParameter = null;
		}
	}
