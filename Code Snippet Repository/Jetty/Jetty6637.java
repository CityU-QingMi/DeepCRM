        @Override
        public void outgoingFrame(Frame frame, WriteCallback callback, BatchMode batchMode)
        {
            try
            {
                this.incoming.incomingFrame(frame);
                callback.writeSuccess();
            }
            catch (Throwable t)
            {
                callback.writeFailed(t);
            }
        }
