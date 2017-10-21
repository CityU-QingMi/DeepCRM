        @Override
        protected Action process() throws Exception
        {
            current = pollEntry();
            if (current == null)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Entering IDLE");
                return Action.IDLE;
            }
            if (LOG.isDebugEnabled())
                LOG.debug("Processing {}",current);
            nextOutgoing.outgoingFrame(current.frame,this,current.batchMode);
            return Action.SCHEDULED;
        }
