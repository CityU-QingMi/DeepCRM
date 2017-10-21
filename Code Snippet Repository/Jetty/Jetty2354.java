        private boolean succeed(Callback callback)
        {
            // Succeeding the callback may cause to reenter in onWritePossible()
            // because typically the callback is the one that controls whether the
            // content received from the server has been consumed, so succeeding
            // the callback causes more content to be received from the server,
            // and hence more to be written to the client by onWritePossible().
            // A reentrant call to onWritePossible() performs another write,
            // which may remain pending, which means that the reentrant call
            // to onWritePossible() returns all the way back to just after the
            // succeed of the callback. There, we cannot just loop attempting
            // write, but we need to check whether we are write pending.
            callback.succeeded();
            return writePending;
        }
