    @Override
    public Object replaceElements(
            Object original,
            Object target,
            CollectionPersister persister,
            Object owner,
            Map copyCache,
            SharedSessionContractImplementor session)
            throws HibernateException {
        Queue result = (Queue) target;
        result.clear();
        result.addAll( (Queue) original );
        return result;
    }
