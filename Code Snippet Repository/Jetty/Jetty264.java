    public void updateResponseListeners(Response.ResponseListener overrideListener)
    {
        // Create a new instance to avoid that iterating over the listeners
        // will notify a listener that may send a new request and trigger
        // another call to this method which will build different listeners
        // which may be iterated over when the iteration continues.
        List<Response.ResponseListener> listeners = new ArrayList<>();
        HttpExchange firstExchange = exchanges.peekFirst();
        HttpExchange lastExchange = exchanges.peekLast();
        if (firstExchange == lastExchange)
        {
            if (overrideListener != null)
                listeners.add(overrideListener);
            else
                listeners.addAll(firstExchange.getResponseListeners());
        }
        else
        {
            // Order is important, we want to notify the last exchange first
            listeners.addAll(lastExchange.getResponseListeners());
            if (overrideListener != null)
                listeners.add(overrideListener);
            else
                listeners.addAll(firstExchange.getResponseListeners());
        }
        this.listeners = listeners;
    }
