        protected void data(byte[] bytes, int offset, int length, Callback callback)
        {
            if (state != WriteState.IDLE)
                throw new WritePendingException();
            this.state = WriteState.READY;
            this.buffer = bytes;
            this.offset = offset;
            this.length = length;
            this.callback = callback;
        }
