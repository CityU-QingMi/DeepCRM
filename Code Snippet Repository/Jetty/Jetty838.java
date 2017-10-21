        @Override
        public void onFailure(int request, Throwable failure)
        {
            HttpChannelOverFCGI channel = channels.get(request);
            if (channel != null)
            {
                if (channel.responseFailure(failure))
                    releaseRequest(request);
            }
            else
            {
                noChannel(request);
            }
        }
