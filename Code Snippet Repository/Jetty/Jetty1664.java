        private boolean select()
        {
            try
            {
                Selector selector = _selector;
                if (selector != null && selector.isOpen())
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("Selector loop waiting on select");
                    int selected = selector.select();
                    if (LOG.isDebugEnabled())
                        LOG.debug("Selector loop woken up from select, {}/{} selected", selected, selector.keys().size());

                    try (Locker.Lock lock = _locker.lock())
                    {
                        // finished selecting
                        _selecting = false;
                    }

                    _keys = selector.selectedKeys();
                    _cursor = _keys.iterator();

                    return true;
                }
            }
            catch (Throwable x)
            {
                closeNoExceptions(_selector);
                if (isRunning())
                    LOG.warn(x);
                else
                    LOG.debug(x);
            }
            return false;
        }
