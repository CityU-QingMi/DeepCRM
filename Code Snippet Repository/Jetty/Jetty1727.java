        private void handshakeFinished()
        {
            if (_handshaken)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Renegotiated {}", SslConnection.this);
                if (_renegotiationLimit>0)
                    _renegotiationLimit--;
            }
            else
            {
                _handshaken = true;
                if (LOG.isDebugEnabled())
                    LOG.debug("{} handshake succeeded {}/{} {}",
                        _sslEngine.getUseClientMode() ? "client" : "resumed server",
                            _sslEngine.getSession().getProtocol(),_sslEngine.getSession().getCipherSuite(),
                            SslConnection.this);
                notifyHandshakeSucceeded(_sslEngine);
            }
        }
