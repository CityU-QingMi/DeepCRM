        @Override
        protected Action process() throws Exception
        {
            if (finished)
            {
                current = pollEntry();
                LOG.debug("Processing {}", current);
                if (current == null)
                    return Action.IDLE;
                fragment(current, true);
            }
            else
            {
                fragment(current, false);
            }
            return Action.SCHEDULED;
        }
