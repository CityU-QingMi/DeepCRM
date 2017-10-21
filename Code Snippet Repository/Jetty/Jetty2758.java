        @Override
        public ByteBuffer getIndirectBuffer()
        {
            ByteBuffer buffer = _indirectBuffer.get();
            if (buffer==null)
            {
                ByteBuffer buffer2=CachedContentFactory.this.getIndirectBuffer(_resource);
                
                if (buffer2==null)
                    LOG.warn("Could not load "+this);
                else if (_indirectBuffer.compareAndSet(null,buffer2))
                {
                    buffer=buffer2;
                    if (_cachedSize.addAndGet(BufferUtil.length(buffer))>_maxCacheSize)
                        shrinkCache();
                }
                else
                    buffer=_indirectBuffer.get();
            }
            if (buffer==null)
                return null;
            return buffer.slice();
        }
