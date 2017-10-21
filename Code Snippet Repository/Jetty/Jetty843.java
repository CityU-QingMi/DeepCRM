    private int acquireRequest()
    {
        synchronized (requests)
        {
            int last = requests.getLast();
            int request = last + 1;
            requests.addLast(request);
            return request;
        }
    }
