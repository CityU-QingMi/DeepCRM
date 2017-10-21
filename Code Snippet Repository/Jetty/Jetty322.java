    @Override
    @SuppressWarnings("")
    public <T extends RequestListener> List<T> getRequestListeners(Class<T> type)
    {
        // This method is invoked often in a request/response conversation,
        // so we avoid allocation if there is no need to filter.
        if (type == null || requestListeners == null)
            return requestListeners != null ? (List<T>)requestListeners : Collections.<T>emptyList();

        ArrayList<T> result = new ArrayList<>();
        for (RequestListener listener : requestListeners)
            if (type.isInstance(listener))
                result.add((T)listener);
        return result;
    }
