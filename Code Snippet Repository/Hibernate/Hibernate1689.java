	@Override
	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		final StringBuilder buf = new StringBuilder( toLeftSqlString( criteria, criteriaQuery ) );
		if ( op != null ) {
			buf.append( ' ' ).append( op ).append( ' ' );
		}
		if ( quantifier != null ) {
			buf.append( quantifier ).append( ' ' );
		}

		final SessionFactoryImplementor factory = criteriaQuery.getFactory();
		final OuterJoinLoadable persister =
				(OuterJoinLoadable) factory.getMetamodel().entityPersister( criteriaImpl.getEntityOrClassName() );

		createAndSetInnerQuery( criteriaQuery, factory );
		criteriaImpl.setSession( deriveRootSession( criteria ) );

		final CriteriaJoinWalker walker = new CriteriaJoinWalker(
				persister,
				innerQuery,
				factory,
				criteriaImpl,
				criteriaImpl.getEntityOrClassName(),
				criteriaImpl.getSession().getLoadQueryInfluencers(),
				innerQuery.getRootSQLALias()
		);

		return buf.append( '(' ).append( walker.getSQLString() ).append( ')' ).toString();
	}
