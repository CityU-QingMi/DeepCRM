    public void notifyComplete(List<Response.ResponseListener> listeners, Result result)
    {
        // Optimized to avoid allocations of iterator instances
        for (int i = 0; i < listeners.size(); ++i)
        {
            Response.ResponseListener listener = listeners.get(i);
            if (listener instanceof Response.CompleteListener)
                notifyComplete((Response.CompleteListener)listener, result);
        }
    }
