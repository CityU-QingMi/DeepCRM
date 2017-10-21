    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        @SuppressWarnings("unchecked")
        Enumeration<String> acceptValues = request.getHeaders("Accept");
        while (acceptValues.hasMoreElements())
        {
            String accept = acceptValues.nextElement();
            if (accept.equals("text/event-stream"))
            {
                EventSource eventSource = newEventSource(request);
                if (eventSource == null)
                {
                    response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                }
                else
                {
                    respond(request, response);
                    AsyncContext async = request.startAsync();
                    // Infinite timeout because the continuation is never resumed,
                    // but only completed on close
                    async.setTimeout(0);
                    EventSourceEmitter emitter = new EventSourceEmitter(eventSource, async);
                    emitter.scheduleHeartBeat();
                    open(eventSource, emitter);
                }
                return;
            }
        }
        super.doGet(request, response);
    }
