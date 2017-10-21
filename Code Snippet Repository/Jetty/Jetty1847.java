    public String makeName(String basis)
    {
        if (basis == null)
            return null;
        return basis
                .replace(':', '_')
                .replace('*', '_')
                .replace('?', '_')
                .replace('=', '_')
                .replace(',', '_')
                .replace(' ', '_');
    }
