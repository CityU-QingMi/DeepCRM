    public void onWriteFailure(Throwable t)
    {
        ConnectionState event = null;
        synchronized (this)
        {
            if (this.state == ConnectionState.CLOSED)
            {
                // already closed
                return;
            }

            // Build out Close Reason
            String reason = "WebSocket Write Failure";
            if (t instanceof EOFException)
            {
                reason = "WebSocket Write EOF";
                Throwable cause = t.getCause();
                if ((cause != null) && (StringUtil.isNotBlank(cause.getMessage())))
                {
                    reason = "EOF: " + cause.getMessage();
                }
            }
            else
            {
                if (StringUtil.isNotBlank(t.getMessage()))
                {
                    reason = t.getMessage();
                }
            }

            CloseInfo close = new CloseInfo(StatusCode.ABNORMAL,reason);

            finalClose.compareAndSet(null,close);

            this.cleanClose = false;
            this.state = ConnectionState.CLOSED;
            this.inputAvailable = false;
            this.outputAvailable = false;
            this.closeHandshakeSource = CloseHandshakeSource.ABNORMAL;
            event = this.state;
        }
        notifyStateListeners(event);
    }
