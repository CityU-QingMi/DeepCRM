        @Override
        public String getProtocolVersion()
        {
            String ver = getHttpFields().get(HttpHeader.SEC_WEBSOCKET_VERSION);
            if (ver == null)
            {
                return Integer.toString(WebSocketConstants.SPEC_VERSION);
            }
            return ver;
        }
