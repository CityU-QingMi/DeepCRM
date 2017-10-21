    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("Param[");
        str.append("index=").append(index);
        str.append(",type=").append(ReflectUtils.toShortName(type));
        str.append(",role=").append(role);
        if (pathParamName != null)
        {
            str.append(",pathParamName=").append(pathParamName);
        }
        str.append(']');
        return str.toString();
    }
