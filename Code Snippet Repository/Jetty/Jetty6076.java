    private boolean visitParam(JsrCallable callable, Param param, List<IJsrParamId> paramIds)
    {
        for (IJsrParamId paramId : paramIds)
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("{}.process()",paramId);
            }
            if (paramId.process(param,callable))
            {
                // Successfully identified
                if (LOG.isDebugEnabled())
                {
                    LOG.debug("Identified: {}",param);
                }
                return true;
            }
        }

        // Failed identification as a known parameter
        return false;
    }
