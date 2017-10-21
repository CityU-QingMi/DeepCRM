        @Override
        public void onDataAvailable() throws IOException
        {
            __history.add("onDataAvailable");
            if (!_noReadInDataAvailable && readAvailable() && _completeInOnDataAvailable)
            {
                __history.add("complete");
                _state.complete();
            }
        }
