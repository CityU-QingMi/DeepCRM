    protected boolean complianceViolation(HttpCompliance compliance,String reason)
    {
        if (_complianceHandler==null)
            return _compliance.ordinal()>=compliance.ordinal();
        if (_compliance.ordinal()<compliance.ordinal())
        {
            _complianceHandler.onComplianceViolation(_compliance,compliance,reason);
            return false;
        }
        return true;
    }
