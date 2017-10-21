    public void notifyHeaders(List<Response.ResponseListener> listeners, Response response)
    {
        // Optimized to avoid allocations of iterator instances
        for (int i = 0; i < listeners.size(); ++i)
        {
            Response.ResponseListener listener = listeners.get(i);
            if (listener instanceof Response.HeadersListener)
                notifyHeaders((Response.HeadersListener)listener, response);
        }
    }
