        @Override
        public void run() {
            try {
                startSignal.await();
                resultMap = adapter.getLoggersInContext(context);
                resultMap.put(String.valueOf(index), new TestLogger());
                doneSignal.countDown();
            }
            catch (final Exception e) {
                e.printStackTrace();
            }
        }
