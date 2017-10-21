    public void send(HttpExchange exchange)
    {
        if (!queuedToBegin(exchange))
            return;

        Request request = exchange.getRequest();
        ContentProvider contentProvider = request.getContent();
        HttpContent content = this.content = new HttpContent(contentProvider);

        SenderState newSenderState = SenderState.SENDING;
        if (expects100Continue(request))
            newSenderState = content.hasContent() ? SenderState.EXPECTING_WITH_CONTENT : SenderState.EXPECTING;

        out: while (true)
        {
            SenderState current = senderState.get();
            switch (current)
            {
                case IDLE:
                case COMPLETED:
                {
                    if (updateSenderState(current, newSenderState))
                        break out;
                    break;
                }
                default:
                {
                    illegalSenderState(current);
                    return;
                }
            }
        }

        // Setting the listener may trigger calls to onContent() by other
        // threads so we must set it only after the sender state has been updated
        if (contentProvider instanceof AsyncContentProvider)
            ((AsyncContentProvider)contentProvider).setListener(this);

        if (!beginToHeaders(exchange))
            return;

        sendHeaders(exchange, content, commitCallback);
    }
