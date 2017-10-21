        @Override
        public boolean flush(ByteBuffer... buffers) throws IOException
        {
            if (__startBlocking.get()==0 || __startBlocking.decrementAndGet()==0)
            {
                if (__blockFor.get()>0 && __blockFor.getAndDecrement()>0)
                {
                    return false;
                }
            }
            boolean flushed=super.flush(buffers);
            return flushed;
        }
