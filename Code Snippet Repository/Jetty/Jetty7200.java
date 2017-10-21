    @Override
    public Set<String> getHeaderNames()
    {
        if (response==null)
            return headers.keySet();
        
        Set<String> h = new HashSet<>(response.getHeaderNames());
        h.addAll(headers.keySet());
        return h;
    }
