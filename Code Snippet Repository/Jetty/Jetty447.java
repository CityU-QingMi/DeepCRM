        @Override
        public void close() throws IOException
        {
            List<Callback> callbacks;
            synchronized (lock)
            {
                if (closed)
                    return;
                closed = true;
                callbacks = drain();
                lock.notifyAll();
            }

            if (LOG.isDebugEnabled())
                LOG.debug("InputStream close");

            Throwable failure = new AsynchronousCloseException();
            callbacks.forEach(callback -> callback.failed(failure));

            super.close();
        }
