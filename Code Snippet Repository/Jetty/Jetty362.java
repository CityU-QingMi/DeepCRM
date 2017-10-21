    public void notifyBegin(Request request)
    {
        // Optimized to avoid allocations of iterator instances
        List<Request.RequestListener> requestListeners = request.getRequestListeners(null);
        for (int i = 0; i < requestListeners.size(); ++i)
        {
            Request.RequestListener listener = requestListeners.get(i);
            if (listener instanceof Request.BeginListener)
                notifyBegin((Request.BeginListener)listener, request);
        }
        List<Request.Listener> listeners = client.getRequestListeners();
        for (int i = 0; i < listeners.size(); ++i)
        {
            Request.Listener listener = listeners.get(i);
            notifyBegin(listener, request);
        }
    }
