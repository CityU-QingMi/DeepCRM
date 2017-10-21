        private void stopLifeCycles(Predicate<LifeCycle> predicate, boolean destroy)
        {
            List<LifeCycle> lifeCycles = new ArrayList<>();
            synchronized (this)
            {
                lifeCycles.addAll(_lifeCycles);
            }

            for (LifeCycle l : lifeCycles)
            {
                try
                {
                    if (l.isStarted() && predicate.test(l))
                        l.stop();

                    if ((l instanceof Destroyable) && destroy)
                        ((Destroyable)l).destroy();
                }
                catch (Throwable x)
                {
                    debug(x);
                }
            }
        }
