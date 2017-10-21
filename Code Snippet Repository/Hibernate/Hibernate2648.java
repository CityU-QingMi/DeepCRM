	public void promoteJoin(FromElement elem) {
		LOG.debugf( "Promoting [%s] to [%s]", elem, this );
		//TODO: implement functionality
		//  this might be painful to do here, as the "join post processing" for
		//  the subquery has already been performed (meaning that for
		//  theta-join dialects, the join conditions have already been moved
		//  over to the where clause).  A "simple" solution here might to
		//  doAfterTransactionCompletion "join post processing" once for the entire query (including
		//  any subqueries) at one fell swoop
	}
