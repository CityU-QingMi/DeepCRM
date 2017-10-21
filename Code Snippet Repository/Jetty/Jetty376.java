        @Override
        protected Action process() throws Exception
        {
            if (index == listeners.size())
                return Action.SUCCEEDED;

            Response.ResponseListener listener = listeners.get(index);
            if (listener instanceof Response.AsyncContentListener)
            {
                // The buffer was sliced, so we always clear it
                // (clear => position=0, limit=capacity) before
                // passing it to the listener that may consume it.
                buffer.clear();
                ResponseNotifier.this.notifyContent((Response.AsyncContentListener)listener, response, buffer, this);
                return Action.SCHEDULED;
            }
            else
            {
                succeeded();
                return Action.SCHEDULED;
            }
        }
