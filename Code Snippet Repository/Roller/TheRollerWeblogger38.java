    protected SyndEntry createSyndEntry(Item item) {
        DCModule dcm = (DCModule)item.getModule(DCModule.URI);
        Date dcdate = dcm != null ? dcm.getDate() : null;
        SyndEntry syndEntry = super.createSyndEntry(item, true);
        if (dcdate != null)
        {
            ((DCModule)syndEntry.getModule(DCModule.URI)).setDate(dcdate);
        }
        return syndEntry;
    }
