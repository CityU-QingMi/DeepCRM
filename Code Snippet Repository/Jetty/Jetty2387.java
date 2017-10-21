        @Override
        protected Action process() throws Exception
        {
            if (!iterator.hasNext())
                return Action.SUCCEEDED;

            ByteBuffer buffer = iterator.next();
            if (buffer == null)
                return Action.IDLE;

            deferred.offer(buffer, this);
            return Action.SCHEDULED;
        }
