        @Override
        protected Action process() throws Exception
        {
            if (finished)
            {
                current = pollEntry();
                LOG.debug("Processing {}",current);
                if (current == null)
                    return Action.IDLE;
                deflate(current);
            }
            else
            {
                compress(current,false);
            }
            return Action.SCHEDULED;
        }
