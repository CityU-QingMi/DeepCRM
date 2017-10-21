    @Override
    public EventDriver create(Object websocket, WebSocketPolicy policy)
    {
        Class<?> websocketClass = websocket.getClass();
        synchronized (this)
        {
            JettyAnnotatedMetadata metadata = cache.get(websocketClass);
            if (metadata == null)
            {
                JettyAnnotatedScanner scanner = new JettyAnnotatedScanner();
                metadata = scanner.scan(websocketClass);
                cache.put(websocketClass,metadata);
            }
            return new JettyAnnotatedEventDriver(policy,websocket,metadata);
        }
    }
