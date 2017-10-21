        public void release(ByteBuffer buffer)
        {
            BufferUtil.clear(buffer);
            if (_space == null)
                queueOffer(buffer);
            else if (_space.decrementAndGet() >= 0)
                queueOffer(buffer);
            else
                _space.incrementAndGet();
        }
