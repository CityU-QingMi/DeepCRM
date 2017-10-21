        public Listener(AsyncContext asyncContext) throws IOException
        {
            this.asyncContext = asyncContext;
            this.response = (HttpServletResponse)asyncContext.getResponse();
            this.input = asyncContext.getRequest().getInputStream();
            this.output = response.getOutputStream();
            CompletableFuture.allOf(inputComplete, outputComplete)
                    .whenComplete((ignoredResult, ignoredThrowable) -> asyncContext.complete());
            // Dispatch setting the write listener to another thread.
            executor.execute(() -> output.setWriteListener(this));
        }
