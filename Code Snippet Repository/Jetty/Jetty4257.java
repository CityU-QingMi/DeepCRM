    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("\n");
        sb.append("configured headers:\n");
        for (ConfiguredHeader c : _configuredHeaders)
            sb.append(c).append("\n");

        return sb.toString();
    }
