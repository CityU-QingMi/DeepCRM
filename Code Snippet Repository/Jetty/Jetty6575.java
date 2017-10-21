    @Override
    public void dump(Appendable out, String indent) throws IOException
    {
        super.dump(out,indent);

        IncomingFrames websocket = getLastIncoming();
        OutgoingFrames network = getLastOutgoing();

        out.append(indent).append(" +- Stack").append(System.lineSeparator());
        out.append(indent).append("     +- Network  : ").append(network.toString()).append(System.lineSeparator());
        for (Extension ext : extensions)
        {
            out.append(indent).append("     +- Extension: ").append(ext.toString()).append(System.lineSeparator());
        }
        out.append(indent).append("     +- Websocket: ").append(websocket.toString()).append(System.lineSeparator());
    }
