        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            try {
                ((CyclicBarrier) context.getScheduler().getContext().get(BARRIER_KEY)).await(20, TimeUnit.SECONDS);
            } catch (SchedulerException ex) {
                throw new JobExecutionException(ex);
            } catch (InterruptedException ex) {
                throw new JobExecutionException(ex);
            } catch (BrokenBarrierException ex) {
                throw new JobExecutionException(ex);
            } catch (TimeoutException ex) {
                throw new JobExecutionException(ex);
            }
        }
