    @Override
    public String getExtendedId(String clusterId, HttpServletRequest request)
    {
        if (!StringUtil.isBlank(_workerName))
        {
            if (_workerAttr==null)
                return clusterId+'.'+_workerName;

            String worker=(String)request.getAttribute(_workerAttr);
            if (worker!=null)
                return clusterId+'.'+worker;
        }
    
        return clusterId;
    }
