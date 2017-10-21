    @Override
    public String toEndPointString()
    {
        // We do a best effort to print the right toString() and that's it.
        try
        {
            boolean valid = _key != null && _key.isValid();
            int keyInterests = valid ? _key.interestOps() : -1;
            int keyReadiness = valid ? _key.readyOps() : -1;
            return String.format("%s{io=%d/%d,kio=%d,kro=%d}",
                    super.toEndPointString(),
                    _currentInterestOps,
                    _desiredInterestOps,
                    keyInterests,
                    keyReadiness);
        }
        catch (Throwable x)
        {
            return String.format("%s{io=%s,kio=-2,kro=-2}", super.toString(), _desiredInterestOps);
        }
    }
