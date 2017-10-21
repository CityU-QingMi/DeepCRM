        @Override
        public void onWebSocketClose(int statusCode, String reason)
        {
            LOG.debug("onWebSocketClose({},{})",statusCode,reason);
            super.onWebSocketClose(statusCode,reason);
            closeCount.incrementAndGet();
            closeCode = statusCode;
            closeReason = reason;
            closeLatch.countDown();
        }
