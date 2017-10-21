        @Override
        public void onCompleteFailure(Throwable x)
        {
            if (active != null)
                active.failed(x);
            active = null;

            while (true)
            {
                Generator.Result result = poll();
                if (result == null)
                    break;
                result.failed(x);
            }
        }
