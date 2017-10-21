        @Override
        protected Action process() throws Exception
        {
            // Only return if EOF has previously been read and thus
            // a write done with EOF=true
            if (_eof)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("EOF of {}", this);
                _in.close();
                closed();
                _channel.getByteBufferPool().release(_buffer);
                return Action.SUCCEEDED;
            }

            // Read from stream until buffer full or EOF
            BufferUtil.clearToFill(_buffer);
            while (_buffer.hasRemaining() && !_eof)
                _eof = (_in.read(_buffer)) < 0;

            // write what we have
            BufferUtil.flipToFlush(_buffer, 0);
            _written += _buffer.remaining();
            write(_buffer, _eof, this);

            return Action.SCHEDULED;
        }
