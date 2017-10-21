	public List multiLoad(
			OuterJoinLoadable persister,
			Serializable[] ids,
			SharedSessionContractImplementor session,
			MultiLoadOptions loadOptions) {
		if ( loadOptions.isOrderReturnEnabled() ) {
			return performOrderedMultiLoad( persister, ids, session, loadOptions );
		}
		else {
			return performUnorderedMultiLoad( persister, ids, session, loadOptions );
		}
	}
