        private Runnable nextAction()
        {
            while (true)
            {
                Runnable action;
                try (Locker.Lock lock = _locker.lock())
                {
                    action = _actions.poll();
                    if (action == null)
                    {
                        // No more actions, so we need to select
                        _selecting = true;
                        return null;
                    }
                }

                if (Invocable.getInvocationType(action)==InvocationType.BLOCKING)
                    return action;

                try
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("Running action {}", action);
                    // Running the change may queue another action.
                    action.run();
                }
                catch (Throwable x)
                {
                    LOG.debug("Could not run action " + action, x);
                }
            }
        }
