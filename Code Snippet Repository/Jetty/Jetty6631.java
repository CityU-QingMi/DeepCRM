        private Action flush()
        {
            if (!BufferUtil.isEmpty(aggregate))
            {
                buffers.add(aggregate);
                if (LOG.isDebugEnabled())
                {
                    LOG.debug("{} flushing aggregate {}",FrameFlusher.this,aggregate);
                }
            }

            // Do not allocate the iterator here.
            for (int i = 0; i < entries.size(); ++i)
            {
                FrameEntry entry = entries.get(i);
                // Skip the "synthetic" frame used for flushing.
                if (entry.frame == FLUSH_FRAME)
                {
                    continue;
                }
                buffers.add(entry.generateHeaderBytes());
                ByteBuffer payload = entry.frame.getPayload();
                if (BufferUtil.hasContent(payload))
                {
                    buffers.add(payload);
                }
            }

            if (LOG.isDebugEnabled())
            {
                LOG.debug("{} flushing {} frames: {}",FrameFlusher.this,entries.size(),entries);
            }

            if (buffers.isEmpty())
            {
                releaseAggregate();
                // We may have the FLUSH_FRAME to notify.
                succeedEntries();
                return Action.IDLE;
            }

            endpoint.write(this,buffers.toArray(new ByteBuffer[buffers.size()]));
            buffers.clear();
            return Action.SCHEDULED;
        }
