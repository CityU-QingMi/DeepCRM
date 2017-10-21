        @Override
        public void onTimeout(AsyncEvent event) throws IOException
        {
            LOG.debug("resume request");
            AsyncContext async = _async.get();
            if (async != null && _async.compareAndSet(async, null))
            {
                HttpServletResponse response = (HttpServletResponse)async.getResponse();
                response.setContentType("text/json;charset=utf-8");
                response.getOutputStream().write("{action:\"poll\"}".getBytes());
                async.complete();
            }
        }
