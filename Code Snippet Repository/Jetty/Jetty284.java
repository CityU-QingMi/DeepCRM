        @Override
        public void send(Request request, Response.CompleteListener listener)
        {
            if (connection.isClosed())
            {
                destination.newConnection(new TunnelPromise(request, listener, promise));
            }
            else
            {
                connection.send(request, listener);
            }
        }
