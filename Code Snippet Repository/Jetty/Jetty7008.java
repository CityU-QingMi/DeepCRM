        @OnWebSocketMessage
        public void onMessage(String message)
        {
            SuspendToken suspendToken = this.session.suspend();
            this.session.getRemote().sendString(message,
                    new WriteCallback()
                    {
                        
                        @Override
                        public void writeSuccess()
                        {
                            suspendToken.resume();
                        }
                        
                        @Override
                        public void writeFailed(Throwable t)
                        {
                            Assert.fail(t.getMessage());
                        }
                    });
        }
