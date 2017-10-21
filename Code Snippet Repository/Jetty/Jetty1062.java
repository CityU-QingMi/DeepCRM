        protected boolean generate(ByteBufferPool.Lease lease)
        {
            int dataRemaining = dataRemaining();

            int sessionSendWindow = getSendWindow();
            int streamSendWindow = stream.updateSendWindow(0);
            int window = Math.min(streamSendWindow, sessionSendWindow);
            if (window <= 0 && dataRemaining > 0)
                return false;

            int length = Math.min(dataRemaining, window);

            // Only one DATA frame is generated.
            bytes = generator.data(lease, (DataFrame)frame, length);
            int written = bytes - Frame.HEADER_LENGTH;
            if (LOG.isDebugEnabled())
                LOG.debug("Generated {}, length/window/data={}/{}/{}", frame, written, window, dataRemaining);

            this.dataWritten = written;
            this.dataRemaining -= written;

            flowControl.onDataSending(stream, written);

            return true;
        }
