    private ObjectName[] queryNames(ObjectName param) 
        throws IOException, MalformedObjectNameException
    {
        ObjectName[] result = null;
        
        MBeanServerConnection connection = JMXMonitor.getServiceConnection();
        Set names = connection.queryNames(param, null);
        if (names != null && names.size() > 0)
        {
            result = new ObjectName[names.size()];
            
            int idx = 0;
            for(Object name : names)
            {
                if (name instanceof ObjectName)
                    result[idx++] = (ObjectName)name;
                else
                    result[idx++] = new ObjectName(name.toString());
            }
        }
        
        return result;
    }
