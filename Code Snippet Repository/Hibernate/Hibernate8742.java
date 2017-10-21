	@SuppressWarnings("")
	private List<BatchJob> nextFiveBatchJobs(Session session, Integer maxResult) {
		Query query = session.createQuery(
				"select j from BatchJob j", BatchJob.class )
				.setMaxResults( maxResult )
				.unwrap( Query.class );
		
		applySkipLocked(query);

		return query.list();
	}
