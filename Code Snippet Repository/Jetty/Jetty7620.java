    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Ajax calls are form encoded
        boolean join = Boolean.parseBoolean(request.getParameter("join"));
        String message = request.getParameter("message");
        String username = request.getParameter("user");

        LOG.debug("doPost called. join={},message={},username={}", join, message, username);
        if (username == null)
        {
            LOG.debug("no paramter user set, sending 503");
            response.sendError(503, "user==null");
            return;
        }

        Map<String, Member> room = getRoom(request.getPathInfo());
        Member member = getMember(username, room);

        if (message != null)
        {
            sendMessageToAllMembers(message, username, room);
        }
        // If a message is set, we only want to enter poll mode if the user is a new user. This is necessary to avoid
        // two parallel requests per user (one is already in async wait and the new one). Sending a message will
        // dispatch to an existing poll request if necessary and the client will issue a new request to receive the
        // next message or long poll again.
        if (message == null || join)
        {
            synchronized (member)
            {
                LOG.debug("Queue size: {}", member._queue.size());
                if (member._queue.size() > 0)
                {
                    sendSingleMessage(response, member);
                }
                else
                {
                    LOG.debug("starting async");
                    AsyncContext async = request.startAsync();
                    async.setTimeout(asyncTimeout);
                    async.addListener(member);
                    member._async.set(async);
                }
            }
        }
    }
