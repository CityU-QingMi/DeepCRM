    public WebSocketServletFactory getWebSocketServletFactory()
    {
        // Try filter approach first
        WebSocketUpgradeFilter filter = (WebSocketUpgradeFilter)this.servlet.getServletContext().getAttribute(WebSocketUpgradeFilter.class.getName());
        if (filter != null)
        {
            return filter.getFactory();
        }

        // Try servlet next
        return (WebSocketServletFactory)this.servlet.getServletContext().getAttribute(WebSocketServletFactory.class.getName());
    }
