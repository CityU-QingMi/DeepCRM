    public void notifyFailure(List<Response.ResponseListener> listeners, Response response, Throwable failure)
    {
        // Optimized to avoid allocations of iterator instances
        for (int i = 0; i < listeners.size(); ++i)
        {
            Response.ResponseListener listener = listeners.get(i);
            if (listener instanceof Response.FailureListener)
                notifyFailure((Response.FailureListener)listener, response, failure);
        }
    }
