        @Override
        public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response)
        {
            InetSocketAddress local = (InetSocketAddress) sec.getUserProperties().get(JsrCreator.PROP_LOCAL_ADDRESS);
            InetSocketAddress remote = (InetSocketAddress) sec.getUserProperties().get(JsrCreator.PROP_REMOTE_ADDRESS);
            
            sec.getUserProperties().put("found.local", local);
            sec.getUserProperties().put("found.remote", remote);
            
            super.modifyHandshake(sec, request, response);
        }
