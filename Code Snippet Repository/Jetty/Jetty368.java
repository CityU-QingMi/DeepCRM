    public void notifyContent(Request request, ByteBuffer content)
    {
        // Slice the buffer to avoid that listeners peek into data they should not look at.
        content = content.slice();
        if (!content.hasRemaining())
            return;
        // Optimized to avoid allocations of iterator instances.
        List<Request.RequestListener> requestListeners = request.getRequestListeners(null);
        for (int i = 0; i < requestListeners.size(); ++i)
        {
            Request.RequestListener listener = requestListeners.get(i);
            if (listener instanceof Request.ContentListener)
            {
                // The buffer was sliced, so we always clear it (position=0, limit=capacity)
                // before passing it to the listener that may consume it.
                content.clear();
                notifyContent((Request.ContentListener)listener, request, content);
            }
        }
        List<Request.Listener> listeners = client.getRequestListeners();
        for (int i = 0; i < listeners.size(); ++i)
        {
            Request.Listener listener = listeners.get(i);
            // The buffer was sliced, so we always clear it (position=0, limit=capacity)
            // before passing it to the listener that may consume it.
            content.clear();
            notifyContent(listener, request, content);
        }
    }
