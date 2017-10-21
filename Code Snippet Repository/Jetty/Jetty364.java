    public void notifyHeaders(Request request)
    {
        // Optimized to avoid allocations of iterator instances
        List<Request.RequestListener> requestListeners = request.getRequestListeners(null);
        for (int i = 0; i < requestListeners.size(); ++i)
        {
            Request.RequestListener listener = requestListeners.get(i);
            if (listener instanceof Request.HeadersListener)
                notifyHeaders((Request.HeadersListener)listener, request);
        }
        List<Request.Listener> listeners = client.getRequestListeners();
        for (int i = 0; i < listeners.size(); ++i)
        {
            Request.Listener listener = listeners.get(i);
            notifyHeaders(listener, request);
        }
    }
