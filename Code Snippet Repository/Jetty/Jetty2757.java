        protected void invalidate()
        {
            ByteBuffer indirect=_indirectBuffer.get();
            if (indirect!=null && _indirectBuffer.compareAndSet(indirect,null))
                _cachedSize.addAndGet(-BufferUtil.length(indirect));
            
            ByteBuffer direct=_directBuffer.get();
           
            if (direct!=null && !BufferUtil.isMappedBuffer(direct) && _directBuffer.compareAndSet(direct,null))
                _cachedSize.addAndGet(-BufferUtil.length(direct));
            
            _cachedFiles.decrementAndGet();
            _resource.close();
        }
