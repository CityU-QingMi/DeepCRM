        @Override
        public ByteBuffer getDirectBuffer()
        {
            ByteBuffer buffer = _directBuffer.get();
            if (buffer==null)
            {
                ByteBuffer mapped = CachedContentFactory.this.getMappedBuffer(_resource);
                ByteBuffer direct = mapped==null?CachedContentFactory.this.getDirectBuffer(_resource):mapped;
                    
                if (direct==null)
                    LOG.warn("Could not load "+this);
                else if (_directBuffer.compareAndSet(null,direct))
                {
                    buffer=direct;
                    if (mapped==null && _cachedSize.addAndGet(BufferUtil.length(buffer))>_maxCacheSize)
                        shrinkCache(); 
                }
                else
                    buffer=_directBuffer.get();
            }
            if (buffer==null)
                return null;
            return buffer.asReadOnlyBuffer();
        }
