        private void onLocalClose()
        {
            if (LOG_CLOSE.isDebugEnabled())
                LOG_CLOSE.debug("Local Close Confirmed {}",close);
            if (close.isAbnormal())
            {
                ioState.onAbnormalClose(close);
            }
            else
            {
                ioState.onCloseLocal(close);
            }
        }
