    @Override
    public boolean unregisterLease(RollerTask task) {

        // query for existing lease record first
        TaskLock taskLock;
        try {
            taskLock = this.getTaskLockByName(task.getName());

            if(taskLock == null) {
                return false;
            }

        } catch (WebloggerException ex) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Error getting TaskLock", ex);
            } else {
                LOG.warn("Error getting TaskLock, enable debug for more info");
            }
            return false;
        }

        // try to release lease, just set lease time to 0
        try {
            Query q = strategy.getNamedUpdate(
                    "TaskLock.updateTimeLeasedByName&Client");
            q.setParameter(1, 0);
            q.setParameter(2, task.getName());
            q.setParameter(3, task.getClientId());
            int result = q.executeUpdate();
            
            if(result == 1) {
                strategy.flush();
                return true;
            }

        } catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Error releasing lease", e);
            } else {
                LOG.warn("Error releasing lease, enable debug for more info");
            }
            return false;
        }

        return false;

    }
