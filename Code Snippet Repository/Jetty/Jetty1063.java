        @Override
        public void succeeded()
        {
            bytesWritten.addAndGet(bytes);
            flowControl.onDataSent(stream, dataWritten);

            // Do we have more to send ?
            DataFrame dataFrame = (DataFrame)frame;
            if (dataRemaining() == 0)
            {
                // Only now we can update the close state
                // and eventually remove the stream.
                if (stream.updateClose(dataFrame.isEndStream(), true))
                    removeStream(stream);
                super.succeeded();
            }
        }
