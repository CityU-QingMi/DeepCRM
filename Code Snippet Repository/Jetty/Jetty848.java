        @Override
        protected Action process() throws Exception
        {
            // Look if other writes are needed.
            Generator.Result result = poll();
            if (result == null)
            {
                // No more writes to do, return.
                return Action.IDLE;
            }

            // Attempt to gather another result.
            // Most often there is another result in the
            // queue so this is a real optimization because
            // it sends both results in just one TCP packet.
            Generator.Result other = poll();
            if (other != null)
                result = result.join(other);

            active = result;
            ByteBuffer[] buffers = result.getByteBuffers();
            endPoint.write(this, buffers);
            return Action.SCHEDULED;
        }
