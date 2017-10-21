    public void setAcceptorPriorityDelta(int acceptorPriorityDelta)
    {
        int old=_acceptorPriorityDelta;
        _acceptorPriorityDelta = acceptorPriorityDelta;
        if (old!=acceptorPriorityDelta && isStarted())
        {
            for (Thread thread : _acceptors)
                thread.setPriority(Math.max(Thread.MIN_PRIORITY,Math.min(Thread.MAX_PRIORITY,thread.getPriority()-old+acceptorPriorityDelta)));
        }
    }
