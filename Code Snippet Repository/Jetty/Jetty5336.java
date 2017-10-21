    public static Lease leaseFrom(Executor executor, Object leasee, int threads)
    {
        if (executor instanceof ThreadPool.SizedThreadPool)
        {
            ThreadBudget budget = ((ThreadPool.SizedThreadPool)executor).getThreadBudget();
            if (budget!=null)
                return budget.leaseTo(leasee,threads);
        }
        return NOOP_LEASE;
    }
