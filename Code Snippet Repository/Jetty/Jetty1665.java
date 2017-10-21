        @Override
        public void run()
        {
            Selector selector = _selector;
            if (selector != null && selector.isOpen())
            {
                Set<SelectionKey> keys = selector.keys();
                _dumps.add(selector + " keys=" + keys.size());
                for (SelectionKey key : keys)
                {
                    try
                    {
                        _dumps.add(String.format("SelectionKey@%x{i=%d}->%s", key.hashCode(), key.interestOps(), key.attachment()));
                    }
                    catch (Throwable x)
                    {
                        LOG.ignore(x);
                    }
                }
            }
            latch.countDown();
        }
