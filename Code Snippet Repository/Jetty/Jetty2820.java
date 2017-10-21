    @Override
    public void onComplianceViolation(HttpCompliance compliance, HttpCompliance required, String reason)
    {
        if (_httpConnection.isRecordHttpComplianceViolations())
        {
            if (_complianceViolations == null)
            {
                _complianceViolations = new ArrayList<>();
            }
            String violation = String.format("%s<%s: %s for %s", compliance, required, reason, getHttpTransport());
            _complianceViolations.add(violation);
            if (LOG.isDebugEnabled())
                LOG.debug(violation);
        }
    }
