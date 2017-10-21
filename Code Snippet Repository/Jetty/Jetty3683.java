        @Override
        public void handle(String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException
        {
            long now=System.currentTimeMillis();
            long start=Long.parseLong(baseRequest.getHeader("start"));
            long received=baseRequest.getTimeStamp();

            _handled.incrementAndGet();
            long delay=received-start;
            if (delay<0)
                delay=0;
            _latencies[2].add(new Long(delay));
            _latencies[3].add(new Long(now-start));

            response.setStatus(200);
            response.getOutputStream().print("DATA "+request.getPathInfo()+"\n\n");
            baseRequest.setHandled(true);

            _latencies[4].add(new Long(System.currentTimeMillis()-start));

            return;
        }
