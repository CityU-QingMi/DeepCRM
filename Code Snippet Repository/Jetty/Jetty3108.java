        @Override
        public void onComplete(AsyncEvent event) throws IOException
        {
            HttpChannelState state = ((AsyncContextEvent)event).getHttpChannelState();

            Request request = state.getBaseRequest();
            final long elapsed = System.currentTimeMillis()-request.getTimeStamp();

            long d=_requestStats.decrement();
            _requestTimeStats.set(elapsed);

            updateResponse(request);

            _asyncWaitStats.decrement();
            
            // If we have no more dispatches, should we signal shutdown?
            if (d==0)
            {
                FutureCallback shutdown = _shutdown.get();
                if (shutdown!=null)
                    shutdown.succeeded();
            }   
        }
