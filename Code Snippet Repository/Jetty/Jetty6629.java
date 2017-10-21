        private Action batch()
        {
            if (aggregate == null)
            {
                aggregate = bufferPool.acquire(bufferSize,true);
                if (LOG.isDebugEnabled())
                {
                    LOG.debug("{} acquired aggregate buffer {}",FrameFlusher.this,aggregate);
                }
            }

            // Do not allocate the iterator here.
            for (int i = 0; i < entries.size(); ++i)
            {
                FrameEntry entry = entries.get(i);

                entry.generateHeaderBytes(aggregate);

                ByteBuffer payload = entry.frame.getPayload();
                if (BufferUtil.hasContent(payload))
                {
                    BufferUtil.append(aggregate,payload);
                }
            }
            if (LOG.isDebugEnabled())
            {
                LOG.debug("{} aggregated {} frames: {}",FrameFlusher.this,entries.size(),entries);
            }
            succeeded();
            return Action.SCHEDULED;
        }
