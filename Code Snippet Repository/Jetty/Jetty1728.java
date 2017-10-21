        private boolean allowRenegotiate(HandshakeStatus handshakeStatus)
        {   
            if (!_handshaken || handshakeStatus == HandshakeStatus.NOT_HANDSHAKING)
                return true;

            if (!isRenegotiationAllowed())
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Renegotiation denied {}", SslConnection.this);
                terminateInput();
                return false;
            }
            
            if (getRenegotiationLimit()==0)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Renegotiation limit exceeded {}", SslConnection.this);
                terminateInput();
                return false;
            }
            
            return true;
        }
