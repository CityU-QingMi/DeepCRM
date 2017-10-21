    public TaskLock getTaskLockByName(String name) throws WebloggerException {
        // do lookup
        TypedQuery<TaskLock> q = strategy.getNamedQuery("TaskLock.getByName", TaskLock.class);
        q.setParameter(1, name);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
