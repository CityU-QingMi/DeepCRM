	public void addContract(Contract contract) {
		if ( ! contracts.add( contract ) ) {
			return;
		}
		if ( contract.getParent() != null ) {
			addContract( contract.getParent() );
		}
		contract.getPlans().add( this );
		for ( Iterator it=contract.getSubcontracts().iterator(); it.hasNext(); ) {
			Contract sub = ( Contract ) it.next();
			addContract( sub );
		}
	}
