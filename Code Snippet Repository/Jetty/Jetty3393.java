        @Override
        public void doNonErrorHandle(String target, Request baseRequest, final HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            final CyclicBarrier resumeBarrier = new CyclicBarrier(1);
            
            if (baseRequest.getDispatcherType()==DispatcherType.ERROR)
            {
                response.sendError(500);
                return;
            }
            
            if (request.getAttribute(CONTEXT_ATTRIBUTE) == null)
            {
                final AsyncContext asyncContext = baseRequest.startAsync();
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            asyncContext.getResponse().getWriter().write("foobar");
                            if (dispatch)
                                asyncContext.dispatch();
                            else
                                asyncContext.complete();
                            resumeBarrier.await(5, TimeUnit.SECONDS);
                        }
                        catch (IOException | TimeoutException | InterruptedException | BrokenBarrierException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }).run();
            }
            try
            {
                resumeBarrier.await(5, TimeUnit.SECONDS);
            }
            catch (InterruptedException | BrokenBarrierException | TimeoutException e)
            {
                e.printStackTrace();
            }
            throw new TestCommitException();
        }
