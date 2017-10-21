        @Override
        public void onEnd(int request)
        {
            HttpChannelOverFCGI channel = channels.get(request);
            if (channel != null)
            {
                if (channel.responseSuccess())
                    releaseRequest(request);
            }
            else
            {
                noChannel(request);
            }
        }
