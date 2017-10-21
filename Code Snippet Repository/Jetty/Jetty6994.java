        @Override
        public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp)
        {
            if (req.hasSubProtocol("fastclose"))
            {
                closeSocket = new FastCloseSocket(calls);
                return closeSocket;
            }

            if (req.hasSubProtocol("fastfail"))
            {
                closeSocket = new FastFailSocket(calls);
                return closeSocket;
            }

            if (req.hasSubProtocol("container"))
            {
                closeSocket = new ContainerSocket(serverFactory,calls);
                return closeSocket;
            }
            return new RFCSocket();
        }
